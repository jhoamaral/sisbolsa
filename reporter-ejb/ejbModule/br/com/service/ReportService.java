package br.com.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.service.generetor.GeneratorEnum;
import br.com.service.reports.ReportModel;

@Stateless
public class ReportService {
	
	public ByteArrayInputStream gerar(ReportModel reportModel, Map<?,?> parameter, Collection<?> collection) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			GeneratorEnum.PDF.value().gerar(reportModel.value(), output, parameter, new JRBeanCollectionDataSource(collection));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(output.toByteArray());
	}
	
	public ByteArrayInputStream gerar(ReportModel reportModel,  Collection<?> collection) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			GeneratorEnum.PDF.value().gerar(reportModel.value(), output, new HashMap<Object,Object>(), new JRBeanCollectionDataSource(collection));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(output.toByteArray());
	}
}
