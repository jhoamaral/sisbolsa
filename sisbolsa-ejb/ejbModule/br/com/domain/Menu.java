package br.com.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@Cacheable
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String descricao;

	private String legenda;

	private String link;

	private Integer ordem;

	//bi-directional many-to-one association to Menu
    @ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="menupai")
	private Menu menu;

	//bi-directional many-to-one association to Menu
	@OneToMany(mappedBy="menu",fetch=FetchType.LAZY)
	private Set<Menu> menus;

    public Menu() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLegenda() {
		return this.legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getOrdem() {
		return this.ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public Set<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
}