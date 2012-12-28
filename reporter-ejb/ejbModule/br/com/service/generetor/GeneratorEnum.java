package br.com.service.generetor;

public enum GeneratorEnum {
	PDF(new PdfGenerator());
	
	Generator generator;
	
	private GeneratorEnum(Generator generator){
		this.generator = generator;
	}
	
	public Generator value(){
		return this.generator;
	}
}
