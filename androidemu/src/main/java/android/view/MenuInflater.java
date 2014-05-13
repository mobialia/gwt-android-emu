package android.view;

public class MenuInflater {

    public void inflate(Menu toAdd, Menu menu) {
        for (MenuItem item : toAdd.menuItems) {
            menu.add(item);
        }
    }
}
