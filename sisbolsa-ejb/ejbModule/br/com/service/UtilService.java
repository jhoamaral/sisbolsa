package br.com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.UUID;

import br.com.domain.Periodoletivo;

import sun.misc.BASE64Encoder;

public class UtilService {

	private static HashMap<String, String> mimeTypesMap;

	public static final String encripta(String senha) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(senha.getBytes());

			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(digest.digest());
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();

			return senha;
		}
	}

	public static final String generateOid() {
		return UUID.randomUUID().toString();
	}

	public static String getHoje() {
		String diaf = null;
		String mesf = null;
		String retorno = null;
		Calendar calendar = new GregorianCalendar();
		Date hoje = new Date();
		calendar.setTime(hoje);
		int semana = calendar.get(Calendar.DAY_OF_WEEK);
		int mes = calendar.get(Calendar.MONTH);
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		int ano = calendar.get(Calendar.YEAR);

		// semana
		switch (semana) {
		case 1:
			diaf = "Domingo";
			break;
		case 2:
			diaf = "Segunda";
			break;
		case 3:
			diaf = "Terça";
			break;
		case 4:
			diaf = "Quarta";
			break;
		case 5:
			diaf = "Quinta";
			break;
		case 6:
			diaf = "Sexta";
			break;
		case 7:
			diaf = "Sábado";
			break;
		}

		// mês
		switch (mes) {
		case 1:
			mesf = "Janeiro";
			break;
		case 2:
			mesf = "Fevereiro";
			break;
		case 3:
			mesf = "Março";
			break;
		case 4:
			mesf = "Abril";
			break;
		case 5:
			mesf = "Maio";
			break;
		case 6:
			mesf = "Junho";
			break;
		case 7:
			mesf = "Julho";
			break;
		case 8:
			mesf = "Agosto";
			break;
		case 9:
			mesf = "Setembro";
			break;
		case 10:
			mesf = "Outubro";
			break;
		case 11:
			mesf = "Novembro";
			break;
		case 12:
			mesf = "Dezembro";
			break;
		}

		retorno = diaf + ", " + dia + " de " + mesf + ", de " + ano;

		return retorno;
	}

	public static void copyfile(String srFile, String dtFile) {
		try {
			File f1 = new File(srFile);
			File f2 = new File(dtFile);
			InputStream in = new FileInputStream(f1);

			// For Append the file.
			// OutputStream out = new FileOutputStream(f2,true);

			// For Overwrite the file.
			OutputStream out = new FileOutputStream(f2);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			System.out.println("File copied.");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getMimeType(String file) {
		if (!(mimeTypesMap instanceof HashMap)) {
			mimeTypesMap = new HashMap<String, String>();
			mimeTypesMap.put("ai", "application/postscript");
			mimeTypesMap.put("aif", "audio/x-aiff");
			mimeTypesMap.put("aifc", "audio/x-aiff");
			mimeTypesMap.put("aiff", "audio/x-aiff");
			mimeTypesMap.put("asc", "text/plain");
			mimeTypesMap.put("atom", "application/atom+xml");
			mimeTypesMap.put("au", "audio/basic");
			mimeTypesMap.put("avi", "video/x-msvideo");
			mimeTypesMap.put("bcpio", "application/x-bcpio");
			mimeTypesMap.put("bin", "application/octet-stream");
			mimeTypesMap.put("bmp", "image/bmp");
			mimeTypesMap.put("cdf", "application/x-netcdf");
			mimeTypesMap.put("cgm", "image/cgm");
			mimeTypesMap.put("class", "application/octet-stream");
			mimeTypesMap.put("cpio", "application/x-cpio");
			mimeTypesMap.put("cpt", "application/mac-compactpro");
			mimeTypesMap.put("csh", "application/x-csh");
			mimeTypesMap.put("css", "text/css");
			mimeTypesMap.put("dcr", "application/x-director");
			mimeTypesMap.put("dif", "video/x-dv");
			mimeTypesMap.put("dir", "application/x-director");
			mimeTypesMap.put("djv", "image/vnddjvu");
			mimeTypesMap.put("djvu", "image/vnddjvu");
			mimeTypesMap.put("dll", "application/octet-stream");
			mimeTypesMap.put("dmg", "application/octet-stream");
			mimeTypesMap.put("dms", "application/octet-stream");
			mimeTypesMap.put("doc", "application/msword");
			mimeTypesMap.put("dtd", "application/xml-dtd");
			mimeTypesMap.put("dv", "video/x-dv");
			mimeTypesMap.put("dvi", "application/x-dvi");
			mimeTypesMap.put("dxr", "application/x-director");
			mimeTypesMap.put("eps", "application/postscript");
			mimeTypesMap.put("etx", "text/x-setext");
			mimeTypesMap.put("exe", "application/octet-stream");
			mimeTypesMap.put("ez", "application/andrew-inset");
			mimeTypesMap.put("flv", "video/x-flv");
			mimeTypesMap.put("gif", "image/gif");
			mimeTypesMap.put("gram", "application/srgs");
			mimeTypesMap.put("grxml", "application/srgs+xml");
			mimeTypesMap.put("gtar", "application/x-gtar");
			mimeTypesMap.put("hdf", "application/x-hdf");
			mimeTypesMap.put("hqx", "application/mac-binhex40");
			mimeTypesMap.put("htm", "text/html");
			mimeTypesMap.put("html", "text/html");
			mimeTypesMap.put("ice", "x-conference/x-cooltalk");
			mimeTypesMap.put("ico", "image/x-icon");
			mimeTypesMap.put("ics", "text/calendar");
			mimeTypesMap.put("ief", "image/ief");
			mimeTypesMap.put("ifb", "text/calendar");
			mimeTypesMap.put("iges", "model/iges");
			mimeTypesMap.put("igs", "model/iges");
			mimeTypesMap.put("jnlp", "application/x-java-jnlp-file");
			mimeTypesMap.put("jp2", "image/jp2");
			mimeTypesMap.put("jpe", "image/jpeg");
			mimeTypesMap.put("jpeg", "image/jpeg");
			mimeTypesMap.put("jpg", "image/jpeg");
			mimeTypesMap.put("js", "application/x-javascript");
			mimeTypesMap.put("kar", "audio/midi");
			mimeTypesMap.put("latex", "application/x-latex");
			mimeTypesMap.put("lha", "application/octet-stream");
			mimeTypesMap.put("lzh", "application/octet-stream");
			mimeTypesMap.put("m3u", "audio/x-mpegurl");
			mimeTypesMap.put("m4a", "audio/mp4a-latm");
			mimeTypesMap.put("m4b", "audio/mp4a-latm");
			mimeTypesMap.put("m4p", "audio/mp4a-latm");
			mimeTypesMap.put("m4u", "video/vndmpegurl");
			mimeTypesMap.put("m4v", "video/x-m4v");
			mimeTypesMap.put("mac", "image/x-macpaint");
			mimeTypesMap.put("man", "application/x-troff-man");
			mimeTypesMap.put("mathml", "application/mathml+xml");
			mimeTypesMap.put("me", "application/x-troff-me");
			mimeTypesMap.put("mesh", "model/mesh");
			mimeTypesMap.put("mid", "audio/midi");
			mimeTypesMap.put("midi", "audio/midi");
			mimeTypesMap.put("mif", "application/vndmif");
			mimeTypesMap.put("mov", "video/quicktime");
			mimeTypesMap.put("movie", "video/x-sgi-movie");
			mimeTypesMap.put("mp2", "audio/mpeg");
			mimeTypesMap.put("mp3", "audio/mpeg");
			mimeTypesMap.put("mp4", "video/mp4");
			mimeTypesMap.put("mpe", "video/mpeg");
			mimeTypesMap.put("mpeg", "video/mpeg");
			mimeTypesMap.put("mpg", "video/mpeg");
			mimeTypesMap.put("mpga", "audio/mpeg");
			mimeTypesMap.put("ms", "application/x-troff-ms");
			mimeTypesMap.put("msh", "model/mesh");
			mimeTypesMap.put("mxu", "video/vndmpegurl");
			mimeTypesMap.put("nc", "application/x-netcdf");
			mimeTypesMap.put("oda", "application/oda");
			mimeTypesMap.put("ogg", "application/ogg");
			mimeTypesMap.put("pbm", "image/x-portable-bitmap");
			mimeTypesMap.put("pct", "image/pict");
			mimeTypesMap.put("pdb", "chemical/x-pdb");
			mimeTypesMap.put("pdf", "application/pdf");
			mimeTypesMap.put("pgm", "image/x-portable-graymap");
			mimeTypesMap.put("pgn", "application/x-chess-pgn");
			mimeTypesMap.put("pic", "image/pict");
			mimeTypesMap.put("pict", "image/pict");
			mimeTypesMap.put("png", "image/png");
			mimeTypesMap.put("pnm", "image/x-portable-anymap");
			mimeTypesMap.put("pnt", "image/x-macpaint");
			mimeTypesMap.put("pntg", "image/x-macpaint");
			mimeTypesMap.put("ppm", "image/x-portable-pixmap");
			mimeTypesMap.put("ppt", "application/vndms-powerpoint");
			mimeTypesMap.put("ps", "application/postscript");
			mimeTypesMap.put("qt", "video/quicktime");
			mimeTypesMap.put("qti", "image/x-quicktime");
			mimeTypesMap.put("qtif", "image/x-quicktime");
			mimeTypesMap.put("ra", "audio/x-pn-realaudio");
			mimeTypesMap.put("ram", "audio/x-pn-realaudio");
			mimeTypesMap.put("ras", "image/x-cmu-raster");
			mimeTypesMap.put("rdf", "application/rdf+xml");
			mimeTypesMap.put("rgb", "image/x-rgb");
			mimeTypesMap.put("rm", "application/vndrn-realmedia");
			mimeTypesMap.put("roff", "application/x-troff");
			mimeTypesMap.put("rtf", "text/rtf");
			mimeTypesMap.put("rtx", "text/richtext");
			mimeTypesMap.put("sgm", "text/sgml");
			mimeTypesMap.put("sgml", "text/sgml");
			mimeTypesMap.put("sh", "application/x-sh");
			mimeTypesMap.put("shar", "application/x-shar");
			mimeTypesMap.put("silo", "model/mesh");
			mimeTypesMap.put("sit", "application/x-stuffit");
			mimeTypesMap.put("skd", "application/x-koan");
			mimeTypesMap.put("skm", "application/x-koan");
			mimeTypesMap.put("skp", "application/x-koan");
			mimeTypesMap.put("skt", "application/x-koan");
			mimeTypesMap.put("smi", "application/smil");
			mimeTypesMap.put("smil", "application/smil");
			mimeTypesMap.put("snd", "audio/basic");
			mimeTypesMap.put("so", "application/octet-stream");
			mimeTypesMap.put("spl", "application/x-futuresplash");
			mimeTypesMap.put("src", "application/x-wais-source");
			mimeTypesMap.put("sv4cpio", "application/x-sv4cpio");
			mimeTypesMap.put("sv4crc", "application/x-sv4crc");
			mimeTypesMap.put("svg", "image/svg+xml");
			mimeTypesMap.put("swf", "application/x-shockwave-flash");
			mimeTypesMap.put("t", "application/x-troff");
			mimeTypesMap.put("tar", "application/x-tar");
			mimeTypesMap.put("tcl", "application/x-tcl");
			mimeTypesMap.put("tex", "application/x-tex");
			mimeTypesMap.put("texi", "application/x-texinfo");
			mimeTypesMap.put("texinfo", "application/x-texinfo");
			mimeTypesMap.put("tif", "image/tiff");
			mimeTypesMap.put("tiff", "image/tiff");
			mimeTypesMap.put("tr", "application/x-troff");
			mimeTypesMap.put("tsv", "text/tab-separated-values");
			mimeTypesMap.put("txt", "text/plain");
			mimeTypesMap.put("ustar", "application/x-ustar");
			mimeTypesMap.put("vcd", "application/x-cdlink");
			mimeTypesMap.put("vrml", "model/vrml");
			mimeTypesMap.put("vxml", "application/voicexml+xml");
			mimeTypesMap.put("wav", "audio/x-wav");
			mimeTypesMap.put("wbmp", "image/vndwapwbmp");
			mimeTypesMap.put("wbmxl", "application/vndwapwbxml");
			mimeTypesMap.put("wml", "text/vndwapwml");
			mimeTypesMap.put("wmlc", "application/vndwapwmlc");
			mimeTypesMap.put("wmls", "text/vndwapwmlscript");
			mimeTypesMap.put("wmlsc", "application/vndwapwmlscriptc");
			mimeTypesMap.put("wrl", "model/vrml");
			mimeTypesMap.put("xbm", "image/x-xbitmap");
			mimeTypesMap.put("xht", "application/xhtml+xml");
			mimeTypesMap.put("xhtml", "application/xhtml+xml");
			mimeTypesMap.put("xls", "application/vndms-excel");
			mimeTypesMap.put("xml", "application/xml");
			mimeTypesMap.put("xpm", "image/x-xpixmap");
			mimeTypesMap.put("xsl", "application/xml");
			mimeTypesMap.put("xslt", "application/xslt+xml");
			mimeTypesMap.put("xul", "application/vndmozillaxul+xml");
			mimeTypesMap.put("xwd", "image/x-xwindowdump");
			mimeTypesMap.put("xyz", "chemical/x-xyz");
			mimeTypesMap.put("zip", "application/zip");
		}
		String[] arquivoArray = file.split("[.]");
		String type = arquivoArray[arquivoArray.length - 1];

		if (mimeTypesMap.containsKey(type)) {
			return mimeTypesMap.get(type);
		} else {
			return "";
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static Object invocaMetodo(Object obj, String metodo, String value) {
		Object[] parametros = { value };
		Class[] tipoParametros = { value.getClass() };
		Object retorno = null;
		try {
			retorno = obj.getClass().getMethod(metodo, tipoParametros).invoke(obj,
					parametros);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;

	}
	
	@SuppressWarnings("rawtypes")
	public static Object invocaMetodo(Object obj, String metodo, Object[] parametros, Class[] tipoParametros) {;
		Object retorno = null;
		try {
			retorno = obj.getClass().getMethod(metodo, tipoParametros).invoke(obj,
					parametros);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;

	}

	@SuppressWarnings("rawtypes")
	public static Object invocaMetodo(Object obj, String metodo) throws RuntimeException {
		Object[] parametros = {};
		Class[] tipoParametros = {};
		Object retorno = null;
		try {
			retorno = obj.getClass().getMethod(metodo, tipoParametros)
					.invoke(obj, parametros);
		} catch (RuntimeException e) {
			throw e;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		return retorno;
	}
	
	public static int calculaPeriodoAtual(Periodoletivo ingresso,Periodoletivo atual){
		int result = 1;
		try{
			int ano = ingresso.getAno();
			int semestre = ingresso.getSemestre();
			result = (atual.getAno()-ano)*2+(atual.getSemestre()-semestre)+1;	
		}catch (Exception e) {
			
		}
			
		return result;
	}
}
