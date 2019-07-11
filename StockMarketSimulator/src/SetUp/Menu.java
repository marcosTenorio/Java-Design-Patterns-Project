package SetUp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	Printing printOut = new Printing();

	Scanner selection = new Scanner(System.in);

	public void ReportMenu(SetUpDb setData) {
		System.out.println();
		System.out.println("| REPORT MENU |");
		System.out.println();
		System.out.println("[1] Highest capital company"); // if its a draw, print both FOR ALL BELOW
		System.out.println("[2] Lowest capital company");
		System.out.println("[3] Highest share investor");
		System.out.println("[4] Most campanies investor");
		System.out.println("[5] Lowest share investor");
		System.out.println("[6] Least companies investor");
		System.out.println("[7] Cancel");
		System.out.println();
		System.out.print("Please make your choice from the options above: ");
		int option = selection.nextInt();

		try {
			switch (option) {
			case 1:
				printOut.calculateCapital(setData);
				printOut.printHighestCapital();
				ReportMenu(setData);
				break;
			case 2:
				printOut.calculateCapital(setData);
				printOut.printLowestCapital();
				ReportMenu(setData);
				break;
			case 3:
				printOut.calculateNumberOfShares(setData);
				printOut.printHighestSharesInvestor();
				ReportMenu(setData);
				break;
			case 4:
				printOut.printCompanies(setData);
				printOut.printMostCompanies();
				ReportMenu(setData);
				break;
			case 5:
				printOut.calculateNumberOfShares(setData);
				printOut.printLowestSharesInvestor();
				ReportMenu(setData);
				break;
			case 6:
				printOut.printCompanies(setData);
				printOut.printLeastCompanies();
				ReportMenu(setData);
				break;
			case 7:
				StartMenu(setData);
				break;
			default:
				System.out.println("");
				System.out.println("No options match your input");
				System.out.println("Please, try again");
				ReportMenu(setData);
			}

		} catch (InputMismatchException e) {
			System.out.println("please, try again");
			selection.next();
			ReportMenu(setData);
		}
	}
	
	
	public void StartMenu(SetUpDb setData) {
		System.out.println();
		System.out.println("| MENU |");
		System.out.println();
		System.out.println("[1] Display Investors");
		System.out.println("[2] Display Companies");
		System.out.println("[3] Start Trading Day");
		System.out.println("[4] Exit");
		System.out.println();
		System.out.print("Please make your choice from the options above: ");
		int option = selection.nextInt();
		
		
		try {
			switch (option) {
			case 1:
				printOut.displayInvestors(setData);
				StartMenu(setData);
				break;
			case 2:
				printOut.displayCompanies(setData);
				StartMenu(setData);
				break;
			case 3:
				TradingDay td = new TradingDay();
		        td.Trade(setData);
		        ReportMenu(setData);
				break;
			case 4:
				System.out.println("thanks for visiting... bye");
				System.exit(0);
				break;
			default:
				System.out.println("");
				System.out.println("No options match your input");
				System.out.println("Please, try again");
				ReportMenu(setData);
			}

		} catch (InputMismatchException e) {
			System.out.println("please, try again");
			selection.next();
			ReportMenu(setData);
		}
	}
}
