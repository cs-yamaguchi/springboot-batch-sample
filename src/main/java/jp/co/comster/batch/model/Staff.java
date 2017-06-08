package jp.co.comster.batch.model;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "staff")
public class Staff {

	@Id
	public Integer id;

	public String name;

	public String tel;

	public String memo;

	public String toString() {
		return String.format("%d,%s,%s,%s", id, name, tel ,memo) ;
	}
}
