/**
 * 
 */
package br.com.sisbolsa.converter;

import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;


public class SqlDateTimeConverter extends DateTimeConverter {
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		super.setTimeZone(TimeZone.getDefault());
		Date date = (Date) super.getAsObject(facesContext, uiComponent, value);

		if (null == date)
			return null;

		return new java.sql.Date(date.getTime());
	}
}