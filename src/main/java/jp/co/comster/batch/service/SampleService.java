package jp.co.comster.batch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.comster.batch.model.Staff;
import jp.co.comster.batch.repository.StaffRepository;

@Service
public class SampleService {

	@Autowired
	StaffRepository staffRepository;

	public List<Staff> getAllStaff() {
		return staffRepository.selectAll();
	}

	public Staff getStaff(int id) {
		return staffRepository.selectById(id);
	}

	public Integer getMaxStaffId() {
		return staffRepository.getMaxId();
	}

    public void save(Staff staff) {
        this.staffRepository.insert(staff);
    }

    public void saveAndThrowRuntimeException(Staff staff) {
        this.save(staff);
        throw new RuntimeException("test");
    }

    @Transactional
    public void saveAndThrowRuntimeExceptionWithTransactional(Staff staff) {
        this.saveAndThrowRuntimeException(staff);
    }

    /**
     * TransactionalアノテーションでExceptionが発生した場合はロールバックされない。
     * ロールバックさせる場合は以下
     * @Transactional(rollbackFor=Exception.class)
     *
     * @param staff
     * @throws Exception
     */
    @Transactional(rollbackFor=Exception.class)
    public void saveAndThrowExceptionWithTransactional(Staff staff) throws Exception {
        this.save(staff);
        throw new Exception("test");
    }

}
