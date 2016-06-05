package cz.concrea.conferences.business.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;


@Service
public class InvoicePdfService {

	public void createInvoicePdf(String filename, String sourceHtmlUrl,  HttpServletResponse response) {

		List<String> pdfCommand = Arrays.asList("/usr/local/bin/wkhtmltopdf.sh", sourceHtmlUrl, "-");
		ProcessBuilder pb = new ProcessBuilder(pdfCommand);
		Process pdfProcess;

		try {
			pdfProcess = pb.start();

			setResponseHeaders(response, filename);
			try (InputStream in = pdfProcess.getInputStream()) {
				writeCreatedPdfFileToResponse(in, response);
				waitForProcessBeforeContinueCurrentThread(pdfProcess);
				requireSuccessfulExitStatus(pdfProcess);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException("PDF generation failed");
			} finally {
				pdfProcess.destroy();
			}
		} catch (IOException ex) {
			throw new RuntimeException("PDF generation failed");
		}
	}
	
	private void writeCreatedPdfFileToResponse(InputStream in, HttpServletResponse response) throws IOException {
        OutputStream out = response.getOutputStream();
        IOUtils.copy(in, out);
        out.flush();
    }
	
	
	private void requireSuccessfulExitStatus(Process process) {
        if (process.exitValue() != 0) {
            throw new RuntimeException("PDFko ee");
        }
    }
	
	private void waitForProcessBeforeContinueCurrentThread(Process process) {
        try {
            process.waitFor();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
	
	private void setResponseHeaders(HttpServletResponse response, String filename) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
    }

}
