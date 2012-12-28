/**
 * 
 */
package br.com.sisbolsa.converter;

import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;


public class DateTimemilisConverter extends DateTimeConverter {
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		super.setTimeZone(TimeZone.getDefault());
		System.out.println("Objeto: "+new java.sql.Date(Long.valueOf(value)));
		return new java.sql.Date(Long.valueOf(value));
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String retorno = String.valueOf(arg2);
		System.out.println("Valor retornado: "+retorno);
		return retorno;
	}
}