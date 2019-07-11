package SetUp;
import java.util.ArrayList;
import java.util.Random;

import company.Company;
import investor.Investor;

public class TradingDay {

	int transaction = 0;
	boolean isOpen = true;

	Random r = new Random();

	public static ArrayList<Company.CompanyBuilder> listOfTradeCompanies = new ArrayList();
	public static ArrayList<Investor.InvestorBuilder> listOfTradeInvestors = new ArrayList();

	public void Trade(SetUpDb setData) {

		while (isOpen) {
			System.out.println("Trading...");

			// get any random company and investor
			int randomCompany = r.nextInt(setData.listOfCompany.size());
			int randomInvestor = r.nextInt(setData.listOfInvestor.size());

			Investor.InvestorBuilder investor = setData.listOfInvestor.get(randomInvestor);
			Company.CompanyBuilder company = setData.listOfCompany.get(randomCompany);

			// check if the sorted company still have shares to sell
			// check if the sorted investor still have money
			// if either is false we sort other company and investor
			if (company.build().getShares() > 0 && investor.build().getBudget() >= company.build().getSharePrice()) {
				// check if the investor has invested in the company before
				if (investor.available(company)) {
					investor.addCompany(company);
					double price = company.build().getSharePrice();
					investor.buyShare(price);
					company.sellShare();
					listOfTradeCompanies.add(company);
					listOfTradeInvestors.add(investor);

					transaction++;

					// if a company sells 10 shares, the share price should double up
					if (company.build().getCount() % 10 == 0) {
						company.setSharePrice(company.build().getSharePrice() * 2);
					}

					// if any 10 shares are sold(from any company), and a company hasn't sold any,
					// the price must reduce in 2%
					if (transaction % 10 == 0) {
						updateSharePrice(setData);
					}

					// check if investors still have money to continue
					checkInvestorContinue(setData);

					// check if companies still have shares to sell
					checkCompanyContinue(setData);

				}
			}
		}
	}

	// if any 10 shares are sold(from any company), and a company hasn't sold any,
	// the price must reduce in 2%
	public void updateSharePrice(SetUpDb setData) {
		ArrayList<Company.CompanyBuilder> lastTen = new ArrayList();

		if (listOfTradeCompanies.size() % 10 == 0) {
			for (int i = (listOfTradeCompanies.size() - 10); i < listOfTradeCompanies.size(); i++) {
				Company.CompanyBuilder company = listOfTradeCompanies.get(i);
				lastTen.add(company);
			}

			for (int i = 0; i < setData.listOfCompany.size(); i++) {
				Company.CompanyBuilder company = setData.listOfCompany.get(i);
				for (int j = 0; j < lastTen.size(); j++) {
					Company.CompanyBuilder tradeCompany = lastTen.get(j);
					if (setData.listOfCompany.contains(tradeCompany)) {
						continue;
					} else {
						company.setSharePrice(company.build().getSharePrice() - (company.build().getSharePrice() * 0.02));
					}
				}
			}
		} else {
			lastTen.clear();
		}
	}

	// loop over investors and check if any of them still have money
	// if all of them have no money the trading day stops
	public void checkInvestorContinue(SetUpDb setData) {
		int count = 0;

		Company.CompanyBuilder company;

		for (int i = 0; i < setData.listOfInvestor.size(); i++) {
			Investor.InvestorBuilder in = setData.listOfInvestor.get(i);
			company = setData.listOfCompany.get(i);

			if (in.build().getBudget() < company.build().getSharePrice()) {
				count++;
			}

			if (count == setData.listOfInvestor.size()) {
				isOpen = false;
			}
			if (isOpen != true) {
				System.out.println("Investors out of money!! Trading Day is over!");
				Menu menu = new Menu();
				menu.ReportMenu(setData);
			}
		}
	}

	// loop over companies and check if any of them still have shares
	// if all of them have no shares the trading day stops
	public void checkCompanyContinue(SetUpDb setData) {
		int count = 0;

		for (int i = 0; i < setData.listOfCompany.size(); i++) {
			Company.CompanyBuilder company = setData.listOfCompany.get(i);
			if (company.build().getShares() <= 0) {
				count++;
			}
			if (count == setData.listOfCompany.size()) {
				isOpen = false;
			}
			if (isOpen != true) {
				System.out.println("Companies out of shares!! Trading Day is over!");
				Menu menu = new Menu();
				menu.ReportMenu(setData);
			}
		}
	}

}
