package br.com.service.generetor;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

public interface Generator {
	void gerar(InputStream inputStream,OutputStream outputStream, Map<?,?> map, JRDataSource jrDataSource) throws JRException;
}
