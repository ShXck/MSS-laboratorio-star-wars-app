import javax.swing.*;

class StarWarsJTable {

    JFrame f;

    public StarWarsJTable(String[][] dataApi){
        f=new JFrame();
        //String data[][]= dataApi;
        String data[][]= dataApi;
        String column[]={"Nombre","Altura","Nacimiento"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);
        f.setSize(300,400);
        f.setVisible(true);
    }
}

public class MainFrontend {

    public static void main(String[] args) {

        Factory factory = new Factory();
        String[][] dataApi = factory.getApiHandler().getCharacters();

        new StarWarsJTable(dataApi);
    }
}


