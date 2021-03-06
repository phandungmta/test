package model;
// Generated May 4, 2019 8:01:01 PM by Hibernate Tools 4.3.5.Final

import java.lang.String;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * NewsType generated by hbm2java
 */
@Entity
@Table(name = "News_Type", schema = "dbo", catalog = "Badminton")
public class NewsType implements java.io.Serializable {

	private Integer id;
	private String name;
	private int active;
	private Set<News> newses = new HashSet<News>(0);

	public NewsType() {
	}

	public NewsType(String name, int active) {
		this.name = name;
		this.active = active;
	}

	public NewsType(String name, int active, Set<News> newses) {
		this.name = name;
		this.active = active;
		this.newses = newses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Active", nullable = false)
	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "newsType")
	public Set<News> getNewses() {
		return this.newses;
	}

	public void setNewses(Set<News> newses) {
		this.newses = newses;
	}

}
