package br.com.dto;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.com.domain.Folha;
import br.com.domain.Item;

public class ItemFolhaDTO {
	
	private Item item;
	private Folha folha;
	
	
	
	public ItemFolhaDTO(Item item, Folha folha) {
		this.item = item;
		this.folha = folha;
	}

	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public Folha getFolha() {
		return folha;
	}
	
	public void setFolha(Folha folha) {
		this.folha = folha;
	}

	public static Set<ItemFolhaDTO> getList(List<Item> items){
		Set<ItemFolhaDTO> retorno = new LinkedHashSet<ItemFolhaDTO>();
		
		for(Item item:items){
			retorno.add(new ItemFolhaDTO(item, item.getFolha()));
		}
		
		return retorno;
	}
	
	@Override
	public boolean equals(Object arg0) {
		return this.item.equals(arg0);
	}
	
	@Override
	public int hashCode() {
		return this.item.hashCode();
	}
	
}
