package DAO;
import java.util.ArrayList;

import investor.Investor;

public interface InvestorDAO {
	
	public ArrayList<Investor.InvestorBuilder> getInvestors();
	public boolean saveInvestor(Investor.InvestorBuilder investor);
}
