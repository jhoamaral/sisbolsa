package br.com.service.generetor;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import br.com.service.reports.ReportModel;

public class PdfGenerator implements Generator{

	@Override
	public void gerar(InputStream inputStream,OutputStream outputStream, Map parameters, JRDataSource jrDataSource) throws JRException {
		JasperRunManager.runReportToPdfStream(inputStream, outputStream, parameters, jrDataSource);
	}

}
