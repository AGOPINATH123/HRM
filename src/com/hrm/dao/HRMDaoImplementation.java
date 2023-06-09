package com.hrm.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hrm.bean.EmployeeBean;
import com.hrm.bean.RequestBean;
import com.hrm.businesslogic.EmployeeIdGenerator;
import com.hrm.constants.EmployeeConstants;
import com.hrm.db.connections.MySqlDBConnection;
import com.hrm.session.SharedObject;



public class HRMDaoImplementation implements HRMDao {
	Logger log=Logger.getLogger(getClass());
	
	private static final EmployeeBean EmployeeBean = null;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet resultSet;
	static int placeHolderValue =1;
	static String employeeId;
	public HRMDaoImplementation(){
		con=MySqlDBConnection.getInstance();
		}
	
	public boolean duplicateData(EmployeeBean employeeBean) {
		boolean dp=false;
		employeeId=employeeBean.getEmployeeId();
		if(employeeId==null){
		 employeeId=EmployeeIdGenerator.getEmployeeId();
		}
		String query="select * from employeebean where EmployeeId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,employeeId);
			resultSet=pstmt.executeQuery();
			while(resultSet.next()) {
				 dp=true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		employeeBean.setEmployeeId(employeeId);
		return dp;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int insertEmployeeData(EmployeeBean employeeBean) {
		int result=0;
		String query="insert into employeebean values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
			while(duplicateData(employeeBean)) {
			 employeeId=null;
			employeeBean.setEmployeeId(employeeId);
			duplicateData(employeeBean);
			}
			
			pstmt=con.prepareStatement(query);
			SharedObject.putInToSession(EmployeeConstants.EMPLOYEE_ID, employeeId);
		pstmt.setString(1,employeeId);
		pstmt.setString(2,employeeBean.getFirstName());
		pstmt.setString(3,employeeBean.getLastName());
		pstmt.setString(4,employeeBean.getFatherName());
		pstmt.setString(5,employeeBean.getGender());
		pstmt.setString(6,employeeBean.getDob());
		pstmt.setString(7,employeeBean.getEmailId());
		pstmt.setLong(8,employeeBean.getMobileNumber());
		pstmt.setLong(9, employeeBean.getAlternateMobileNumber());
		pstmt.setLong(10,employeeBean.getAadharNo());
		pstmt.setString(11, employeeBean.getPanNumber());
		pstmt.setString(12, employeeBean.getPassportNumber());
		pstmt.setString(13,employeeBean.getPermanentAddress());
		pstmt.setString(14,employeeBean.getLocalAddress());
		pstmt.setString(15,employeeBean.getSchoolName());
		pstmt.setInt(16, employeeBean.getTenthPassedOut());
		pstmt.setString(17,employeeBean.getIntermediateCollegeName());
		pstmt.setInt(18,employeeBean.getInterPassedOut());
		pstmt.setString(19,employeeBean.getGraduationDetails());
		pstmt.setInt(20,employeeBean.getGraduationPassedOut());
		pstmt.setString(21,employeeBean.getBranch());
		pstmt.setString(22,employeeBean.getUniversity());
		pstmt.setString(23,employeeBean.getCompanyName());
		pstmt.setString(24,employeeBean.getRole());
		pstmt.setString(25,employeeBean.getLocation());
		pstmt.setString(26,employeeBean.getExperience());
		pstmt.setDouble(27,employeeBean.getCurrentCTC());
		pstmt.setString(28, employeeBean.getManager());
		pstmt.setString(29,employeeBean.getBankname());
		pstmt.setLong(30, employeeBean.getBankAccNo());
		pstmt.setString(31,employeeBean.getIfscCode());
		result=pstmt.executeUpdate();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return result;
		
	}
	@Override
	public List<EmployeeBean> search() {
		List<EmployeeBean> listOfEmployee = new ArrayList<EmployeeBean>();
		String query="select EmployeeId,FirstName,FatherName,Gender,EmailId,MobileNo,AadharNo,SchoolName,TenthPassedOut,IntermeadiateCollegeName,InterPassedOut,GraduationDetails,GraduationPassedOut,Branch,University,CompanyName,Role,NoticePeriod,Location,Experience,CurrentCtc,ExpectedCtc,PermanatAddress,LocalAddress from employeebean";
		
		try {
			pstmt = con.prepareStatement(query);
			resultSet=pstmt.executeQuery();
			EmployeeBean employeeBean=null;
			while(resultSet.next()){
				employeeBean = new EmployeeBean();
				employeeBean.setEmployeeId(resultSet.getString(1));
				employeeBean.setFirstName(resultSet.getString(2));
				employeeBean.setLastName(resultSet.getString(3));
				employeeBean.setFatherName(resultSet.getString(3));
				employeeBean.setGender(resultSet.getString(4));				
			
				employeeBean.setEmailId(resultSet.getString(5));
				employeeBean.setMobileNumber((resultSet.getLong(6)));
				employeeBean.setAadharNo(resultSet.getLong(7));
				
				employeeBean.setSchoolName(resultSet.getString(8));
				employeeBean.setTenthPassedOut(resultSet.getInt(9));
				employeeBean.setIntermediateCollegeName(resultSet.getString(10));
				employeeBean.setInterPassedOut(resultSet.getInt(11));
				employeeBean.setGraduationDetails(resultSet.getString(12));
				employeeBean.setGraduationPassedOut(resultSet.getInt(13));
				employeeBean.setBranch(resultSet.getString(14));
				employeeBean.setUniversity(resultSet.getString(15));
				employeeBean.setCompanyName(resultSet.getString(16));
				employeeBean.setRole(resultSet.getString(17));
				employeeBean.setNoticePeriod(resultSet.getInt(18));
				employeeBean.setLocation(resultSet.getString(19));
				employeeBean.setExperience(resultSet.getString(20));
				employeeBean.setCurrentCTC((resultSet.getDouble(21)));
				employeeBean.setExpectedCTC((resultSet.getDouble(22)));
				employeeBean.setPermanentAddress(resultSet.getString(23));
				employeeBean.setLocalAddress(resultSet.getString(24));
				
				listOfEmployee.add(employeeBean);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listOfEmployee;
	}
	
	public List<EmployeeBean> search(String param)
	{
		log.info("search param value="+param);
		if(null == param || param.isEmpty()){
			return null;
		}
		
		List<EmployeeBean> listOfEmployee = new ArrayList<EmployeeBean>();
		EmployeeBean employeeBean = null;
		String query="select * from employeebean where employeeId='"+param +"' or firstname='"+param +"' or currentctc='"+param +"'";
		log.info("search param value="+query);
		/*String query1="select * from employeebean where fatherName=param";
		String query2="select * from employeebean where CurrentCTC=param";
	*/	
		try
		{
			/*pstmt=con.prepareStatement(query);
			//pstmt=con.prepareStatement(query1);
			//pstmt=con.prepareStatement(query2);
			pstmt.setInt(1, param);*/
			
			stmt = con.createStatement();
			
			resultSet=stmt.executeQuery(query);
			while(resultSet.next())
			{
				employeeBean= new EmployeeBean();
				employeeBean.setEmployeeId(resultSet.getString(1));
				employeeBean.setFirstName(resultSet.getString(2));
				employeeBean.setLastName(resultSet.getString(3));
				employeeBean.setFatherName(resultSet.getString(4));
				employeeBean.setGender(resultSet.getString(5));				
				employeeBean.setDob(resultSet.getDate(6).toString());
				//System.out.println(resultSet.getDate(6));
				//System.out.println("hello");
				employeeBean.setEmailId(resultSet.getString(7));
				employeeBean.setMobileNumber(resultSet.getLong(8));
				employeeBean.setAlternateMobileNumber(resultSet.getLong(9));
				employeeBean.setAadharNo(resultSet.getLong(10));
				employeeBean.setPanNumber(resultSet.getString(11));
				employeeBean.setPassportNumber(resultSet.getString(12));
				employeeBean.setPermanentAddress(resultSet.getString(13));
				employeeBean.setLocalAddress(resultSet.getString(14));
				employeeBean.setSchoolName(resultSet.getString(15));
				employeeBean.setTenthPassedOut(resultSet.getInt(16));
				employeeBean.setIntermediateCollegeName(resultSet.getString(17));
				employeeBean.setInterPassedOut(resultSet.getInt(18));
				employeeBean.setGraduationDetails(resultSet.getString(19));
				employeeBean.setGraduationPassedOut(resultSet.getInt(20));
				employeeBean.setBranch(resultSet.getString(21));
				employeeBean.setUniversity(resultSet.getString(22));
				employeeBean.setCompanyName(resultSet.getString(23));
				employeeBean.setRole(resultSet.getString(24));
//				employeeBean.setNoticePeriod(resultSet.getInt(22));
				employeeBean.setLocation(resultSet.getString(25));
				employeeBean.setExperience(resultSet.getString(26));
				employeeBean.setCurrentCTC(resultSet.getDouble(27));
//				employeeBean.setExpectedCTC((resultSet.getDouble(26)));
				employeeBean.setManager(resultSet.getString(28));
				employeeBean.setBankname(resultSet.getString(29));
				employeeBean.setBankAccNo(resultSet.getLong(30));
				employeeBean.setIfscCode(resultSet.getString(31));
				listOfEmployee.add(employeeBean);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		log.info("search response="+listOfEmployee);
		return listOfEmployee;
	}
	
	@SuppressWarnings("null")
	public List<EmployeeBean> searchManager(String role1)
	{
		EmployeeBean employeeBean=null;
		List<EmployeeBean> listOfEmployee = new ArrayList<EmployeeBean>();
	
		String query="select * from employeebean where Role='Project Manager'";
			try
			{
				pstmt=con.prepareStatement(query);
				//pstmt.setString(1,role1);
				resultSet=pstmt.executeQuery();
				while(resultSet.next())
				{
					employeeBean = new EmployeeBean();
					employeeBean.setEmployeeId(resultSet.getString(1));
					employeeBean.setFirstName(resultSet.getString(2));
					employeeBean.setLastName(resultSet.getString(3));
					listOfEmployee.add(employeeBean);
				}
				
				//listOfEmployee.add(employeeBean);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
			return listOfEmployee;
		
	}
	public List<EmployeeBean> searchManagerEmp(String manager)
	{
		EmployeeBean employeeBean=null;
		List<EmployeeBean> listOfEmployee = new ArrayList<EmployeeBean>();
	
		String query="select * from employeebean where Manager=?";
			try
			{
				pstmt=con.prepareStatement(query);
				pstmt.setString(1,manager);
				resultSet=pstmt.executeQuery();
				while(resultSet.next())
				{
					employeeBean = new EmployeeBean();
					employeeBean.setEmployeeId(resultSet.getString(1));
					employeeBean.setFirstName(resultSet.getString(2));
					employeeBean.setLastName(resultSet.getString(3));
					employeeBean.setFatherName(resultSet.getString(4));
					employeeBean.setGender(resultSet.getString(5));				
					
					employeeBean.setEmailId(resultSet.getString(7));
					employeeBean.setMobileNumber(resultSet.getLong(8));
					employeeBean.setAadharNo(resultSet.getLong(9));
					employeeBean.setPermanentAddress(resultSet.getString(10));
					employeeBean.setLocalAddress(resultSet.getString(11));
					employeeBean.setSchoolName(resultSet.getString(12));
					employeeBean.setTenthPassedOut(resultSet.getInt(13));
					employeeBean.setIntermediateCollegeName(resultSet.getString(14));
					employeeBean.setInterPassedOut(resultSet.getInt(15));
					employeeBean.setGraduationDetails(resultSet.getString(16));
					employeeBean.setGraduationPassedOut(resultSet.getInt(17));
					employeeBean.setBranch(resultSet.getString(18));
					employeeBean.setUniversity(resultSet.getString(19));
					employeeBean.setCompanyName(resultSet.getString(20));
					employeeBean.setRole(resultSet.getString(21));
					employeeBean.setNoticePeriod(resultSet.getInt(22));
					employeeBean.setLocation(resultSet.getString(23));
					employeeBean.setExperience(resultSet.getString(24));
					employeeBean.setCurrentCTC(resultSet.getDouble(25));
					employeeBean.setExpectedCTC((resultSet.getDouble(26)));
					employeeBean.setManager(resultSet.getString(27));
					listOfEmployee.add(employeeBean);
				}
				
				//listOfEmployee.add(employeeBean);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
			return listOfEmployee;
		
	}
	public EmployeeBean searchGmailId(String str1)
	{
		EmployeeBean employeeBean=new EmployeeBean();
		String query="select EmailId from employeebean where FirstName=?";
		try
		{
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,str1);
			resultSet=pstmt.executeQuery();
			while(resultSet.next())
			{
				employeeBean.setEmailId(resultSet.getString(1));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return employeeBean;
	}
	
	
	public boolean login(String employeeId,long mobileNumber)
	{
		boolean s1=false;
		try{
		String q1="select * from employeebean where employeeId=? and mobileNo=?";
		con=MySqlDBConnection.getInstance();
		pstmt=con.prepareStatement(q1);
		pstmt.setString(1,employeeId);
		pstmt.setLong(2,mobileNumber);
		resultSet=pstmt.executeQuery();
		s1=resultSet.next();
		}
		catch(Exception e)
		{
			System.out.println("");
		e.printStackTrace();
		}
		return s1;
	}
	@Override
	public int employeeUpdate(EmployeeBean employeeBean) {
		int result =0;
		//update employeebean set employeeName = ? where employeeId=?;
		StringBuffer query = new StringBuffer();
		buildQuery(employeeBean, query);
		
		
		try {
			pstmt = con.prepareStatement(query.toString());
			setPlaceHolderValuesToPreparedStatement(employeeBean);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	/**
	 * @param employeeBean
	 */
	private void setPlaceHolderValuesToPreparedStatement(EmployeeBean employeeBean) {
		addPlaceHolderValue(pstmt,employeeBean.getFirstName());
		addPlaceHolderValue(pstmt, employeeBean.getLastName());
		addPlaceHolderValue(pstmt,employeeBean.getFatherName());
		addPlaceHolderValue(pstmt,employeeBean.getGender());
		addPlaceHolderValue(pstmt,employeeBean.getDob());
		addPlaceHolderValue(pstmt,employeeBean.getEmailId());
		addPlaceHolderValue(pstmt,employeeBean.getMobileNumber());
		addPlaceHolderValue(pstmt,employeeBean.getAadharNo());
		addPlaceHolderValue(pstmt,employeeBean.getPermanentAddress());
		addPlaceHolderValue(pstmt,employeeBean.getLocalAddress());
		addPlaceHolderValue(pstmt,employeeBean.getSchoolName());
		addPlaceHolderValue(pstmt,employeeBean.getTenthPassedOut());
		addPlaceHolderValue(pstmt,employeeBean.getIntermediateCollegeName());
		addPlaceHolderValue(pstmt,employeeBean.getInterPassedOut());
		addPlaceHolderValue(pstmt,employeeBean.getGraduationDetails());
		addPlaceHolderValue(pstmt,employeeBean.getGraduationPassedOut());
		addPlaceHolderValue(pstmt,employeeBean.getBranch());
		addPlaceHolderValue(pstmt,employeeBean.getUniversity());
		addPlaceHolderValue(pstmt,employeeBean.getCompanyName());
		addPlaceHolderValue(pstmt,employeeBean.getRole());
		addPlaceHolderValue(pstmt,employeeBean.getNoticePeriod());
		addPlaceHolderValue(pstmt,employeeBean.getLocation());
		addPlaceHolderValue(pstmt,employeeBean.getExperience());
		addPlaceHolderValue(pstmt,employeeBean.getCurrentCTC());
		addPlaceHolderValue(pstmt,employeeBean.getExpectedCTC());
		addPlaceHolderValue(pstmt, employeeBean.getEmployeeId());
	}
	/**
	 * @param employeeBean
	 * @param query
	 */
	private void buildQuery(EmployeeBean employeeBean, StringBuffer query) {
		query.append("update employeebean set ");
		addToQuery(query, employeeBean.getFirstName(),"firstname");
		addToQuery(query, employeeBean.getLastName(),"lastname");
		addToQuery(query, employeeBean.getFatherName(), "fathername");
		addToQuery(query, employeeBean.getGender(), "gender");
		addToQuery(query, employeeBean.getDob(), "dateofbirth");
		addToQuery(query, employeeBean.getEmailId(), "emailid");
		addToQuery(query, employeeBean.getMobileNumber(), "mobileno");
		addToQuery(query, employeeBean.getAadharNo(), "aadharno");
		addToQuery(query, employeeBean.getPermanentAddress(), "permanataddress");
		addToQuery(query, employeeBean.getLocalAddress(), "localaddress");
		addToQuery(query, employeeBean.getSchoolName(), "schoolname");
		addToQuery(query, employeeBean.getTenthPassedOut(), "tenthpassedout");
		addToQuery(query, employeeBean.getIntermediateCollegeName(), "intermeadiatecollegename");
		addToQuery(query, employeeBean.getInterPassedOut(), "interpassedout");
		addToQuery(query, employeeBean.getGraduationDetails(), "graduationdetails");
		addToQuery(query, employeeBean.getGraduationPassedOut(), "graduationpassedout");
		addToQuery(query, employeeBean.getBranch(), "branch");
		addToQuery(query, employeeBean.getUniversity(), "university");
		addToQuery(query, employeeBean.getCompanyName(),"companyname");
		addToQuery(query, employeeBean.getRole(), "role");
		addToQuery(query, employeeBean.getNoticePeriod(), "noticeperiod");
		addToQuery(query, employeeBean.getLocation(), "location");
		addToQuery(query, employeeBean.getExperience(), "experience");
		addToQuery(query, employeeBean.getCurrentCTC(), "currentctc");
 		addToQuery(query, employeeBean.getExpectedCTC(), "expectedctc");
 		query.replace(query.length()-2, query.length(), "");
		query.append(" where employeeId=?");
	}
	/**
	 * @param prepared
	 * @param param
	 */
	public static void addPlaceHolderValue(PreparedStatement prepared, Object param){
		if(param!=null){
			try {
				if(param instanceof Long && (Long.parseLong(param.toString())) !=0){
					prepared.setLong(placeHolderValue, ((Long) param).longValue());
					placeHolderValue++;
				} else if(param instanceof Integer && (Integer.parseInt(param.toString())) != 0){
					prepared.setInt(placeHolderValue, ((Integer) param).intValue());
					placeHolderValue++;
				} else if(param instanceof Double && (Double.parseDouble(param.toString())) != 0.0){
					prepared.setDouble(placeHolderValue, ((Double) param).doubleValue());
					placeHolderValue++;
				}
				else if(param instanceof String && param!=null && !param.toString().isEmpty()){
				prepared.setString(placeHolderValue, (String) param);
				placeHolderValue++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void addToQuery(StringBuffer param, Object param2, String param3){
		if(param2 instanceof Long && (Long.parseLong(param2.toString())) !=0){
			param.append(param3+"=?, ");
		} else if(param2 instanceof Integer && (Integer.parseInt(param2.toString())) != 0){
			param.append(param3+"=?, ");
		} else if(param2 instanceof Double && (Double.parseDouble(param2.toString())) != 0.0){
			param.append(param3+"=?, ");
		}
		else if(param2 instanceof String && param2!=null && !param2.toString().isEmpty()){
			param.append(param3+"=?, ");
		}
	}
	public int insertRequestData(RequestBean employeerequest){
		String query="insert into CreateRequest values(?,?,?,?,?)";
		int result=0;
		try
		{
			pstmt=con.prepareStatement(query);	
		pstmt.setInt(1,100);
		pstmt.setString(2,employeerequest.getReasonName());
		pstmt.setString(3,employeerequest.getTextArea());
		pstmt.setString(4,employeerequest.getSenderMail());
		pstmt.setString(5,employeerequest.getRecieverMail());
		result=pstmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public List<RequestBean> responseRequestData(){
		List<RequestBean> responseResult = new ArrayList<RequestBean>();
		String query = "select * from responsebean";
		try {
		pstmt=con.prepareStatement(query);
		resultSet=pstmt.executeQuery();
		RequestBean req = null;
		while(resultSet.next()) {
		req=new RequestBean();
		req.setReasonId(resultSet.getString(1));
		req.setReasonName(resultSet.getString(2));
		req.setTextArea(resultSet.getString(3));
		req.setSenderMail(resultSet.getString(4));
		req.setRecieverMail(resultSet.getString(5));
		req.setStatus(resultSet.getString(6));
		responseResult.add(req);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return responseResult;
		}

	@Override
	public int closeStatus(RequestBean reqbean) {
		int reqclose = 0;
		String query ="update responsebean set Status=?,ManagerReason=? where ReasonId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, reqbean.getStatus());
			pstmt.setString(2, reqbean.getManagerReason());
			pstmt.setString(3, reqbean.getReasonId());
			reqclose =pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reqclose;
	}
	@Override
	public int responseUpdate(RequestBean reqbean1) {
		int reqresponse=0;
		String query="update responsebean set Status=?,ManagerReason=?,ReAssingMailId=? where ReasonId=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, reqbean1.getStatus());
			pstmt.setString(2, reqbean1.getManagerReason());
			pstmt.setString(3, reqbean1.getReAssignMailId());
			pstmt.setString(4, reqbean1.getReasonId());
			reqresponse=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reqresponse;
	}
}