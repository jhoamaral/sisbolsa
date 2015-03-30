package br.com.service.generetor;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class PdfGenerator implements Generator{

	@Override
	public void gerar(InputStream inputStream,OutputStream outputStream, Map<String,Object> parameters, JRDataSource jrDataSource) throws JRException {
		JasperRunManager.runReportToPdfStream(inputStream, outputStream, parameters, jrDataSource);
	}

}
