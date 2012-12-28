package br.com.generic;


public abstract class AbstractEntityDomain {
	
	public abstract void setId(String id);
	
	public abstract String getId();
	
	@Override
    public int hashCode() {
    	return this.toString().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
    	return this.hashCode() == obj.hashCode();
    }
    
    @Override
    public String toString() {
    	return String.valueOf(this.getId());
    }
}
