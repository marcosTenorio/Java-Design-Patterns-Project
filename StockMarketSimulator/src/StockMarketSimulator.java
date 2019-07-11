import SetUp.Menu;
import SetUp.SetUpDb;

public class StockMarketSimulator {

	public static void main(String[] args) {
		
		SetUpDb setData = new SetUpDb();
        Menu menu = new Menu();
        setData.getItReady();
        menu.StartMenu(setData);
	}

}
