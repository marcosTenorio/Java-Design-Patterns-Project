package DAO;
import java.util.ArrayList;

import company.Company;

public interface CompanyDAO {
	
	public ArrayList<Company.CompanyBuilder> getCompanies();
	public boolean saveCompany(Company.CompanyBuilder company);
}
