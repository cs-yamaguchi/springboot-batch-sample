package jp.co.comster.batch.repository;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import jp.co.comster.batch.model.Staff;

@ConfigAutowireable
@Dao
public interface StaffRepository {

	@Insert
	@Transactional
	int insert(Staff entity);

	@Update
	@Transactional
	int update(Staff entity);

	@Delete
	@Transactional
	int delete(Staff entity);

	@Select
	List<Staff> selectAll();

	@Select
	Staff selectById(Integer id);

	@Select
	Integer getMaxId();

}
