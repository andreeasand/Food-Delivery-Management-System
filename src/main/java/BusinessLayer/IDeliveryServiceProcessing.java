package BusinessLayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface IDeliveryServiceProcessing {

    /**
     *
     * @param username
     * @param password
     * @return true daca clientul nu este in baza de date sau false in caz contrar
     * @throws IOException
     */
    public boolean register(String username, String password) throws IOException;

    /**
     *
     * @param username
     * @param password
     * @return true daca datele introduse se afla in baza de date
     * @throws FileNotFoundException
     */
    public boolean logIn(String username, String password) throws FileNotFoundException;

    /**
     *
     * @param title
     * @param rating
     * @param calories
     * @param proteins
     * @param fats
     * @param sodium
     * @param price
     * @throws FileNotFoundException
     */
    public void addBaseMenuItem(String title, String rating, String calories, String proteins, String fats, String sodium, String price) throws FileNotFoundException;

    /**
     *
     * @param title
     * @param menuItems
     * @throws FileNotFoundException
     */
    public void addCompositeMenuItem(String title, ArrayList<MenuItem> menuItems) throws FileNotFoundException;

    /**
     *
     * @param title
     * @param rating
     * @param calories
     * @param proteins
     * @param fats
     * @param sodium
     * @param price
     * @throws FileNotFoundException
     */
    public void editMenuItem(String title, String rating, String calories, String proteins, String fats, String sodium, String price) throws FileNotFoundException;

    /**
     *
     * @param menuItem
     * @throws FileNotFoundException
     */
    public void deleteMenuItem(String menuItem) throws FileNotFoundException;

    /**
     *
     * @param products
     * @param id
     * @throws FileNotFoundException
     */
    public void addOrder(ArrayList<String> products, int id) throws FileNotFoundException;

    /**
     *
     * @param reportDate
     */
    public void generateReports(ReportDate reportDate);


    /**
     *
     * @param start
     * @param end
     * @return o lista de comenzi selectate
     */
    public List<Order> timeInterval(int start, int end);
    public List<MenuItem> orderedTimes(int nb);
    public List<Client> clientsSelected(int nb, int price);
    public Map<Object, Object> getWithinDay(int day, int month, int year);

    /**
     *
     * @param order
     * @throws FileNotFoundException
     */
    public void bill(Order order) throws FileNotFoundException;
    public List<MenuItem> getMenu();
    public void setMenu(List<MenuItem> menu);
    public List<Client> getClients();
    public void setClients(List<Client> clients);
    public HashMap<Order, ArrayList<MenuItem>> getOrders();
    public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders);
    public boolean isWellFormed();
    public ArrayList<MenuItem> searchProducts(String title, String rating, String calories, String protein, String fat, String sodium, String price);


}