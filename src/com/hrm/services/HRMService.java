package com.hrm.services;

import java.util.List;

import com.hrm.bean.EmployeeBean;
import com.hrm.bean.RequestBean;

public interface HRMService {
	public abstract int insertEmployeeData(EmployeeBean employeeBean);
	public abstract List<EmployeeBean> search();
	public abstract List<EmployeeBean> search(String param) throws Exception;
	public abstract boolean login(String employeeeId, long mobileNumber);
	public abstract int employeeUpdate(EmployeeBean emp) ;
	public List<EmployeeBean> searchManager(String role1);
	public EmployeeBean searchGmailId(String str1);
	public List<EmployeeBean> searchManagerEmp(String Manager);
	public abstract int insertRequestData(RequestBean requestBean);
	public List<RequestBean> responseRequestData();
	public abstract int closeStatus(RequestBean reqbean);
	public abstract int responseUpdate(RequestBean reqbean1);

}
