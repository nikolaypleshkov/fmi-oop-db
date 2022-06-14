import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyFrame extends JFrame {
    Connection connection = null;
    PreparedStatement state = null;

    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);

    JTable table1 = new JTable();
    JScrollPane jScrollPane1 = new JScrollPane(table1);

    JTable table2 = new JTable();
    JScrollPane jScrollPane2 = new JScrollPane(table2);

    JTable tableQuery = new JTable();
    JScrollPane jScrollQ = new JScrollPane(tableQuery);

    JTable tableQuery1 = new JTable();
    JScrollPane getjScrollQ1 = new JScrollPane(tableQuery1);

    JComboBox jComboBox = new JComboBox();
    JComboBox jComboBox1 = new JComboBox();

    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel movie = new JPanel();
    JPanel director = new JPanel();
    JPanel genre = new JPanel();
    JPanel query1 = new JPanel();
    JPanel query2 = new JPanel();


    JPanel upPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel downPanel = new JPanel();

    JPanel directorUpPanel = new JPanel();
    JPanel directorMidPanel = new JPanel();
    JPanel directorDownPanel = new JPanel();

    JPanel genreUpPanel = new JPanel();
    JPanel genreMidPanel = new JPanel();
    JPanel genreDownPanel = new JPanel();

    JLabel titleLabel = new JLabel("Movie Title");
    JLabel typeLabel = new JLabel("Genre");
    JLabel yearLabel = new JLabel("Release Year");
    JLabel creatorLabel = new JLabel("Director");

    JTextField titleTField = new JTextField();
    JTextField typeTField = new JTextField();
    JTextField yearTField = new JTextField();
    JTextField creatorTField = new JTextField();

    JButton add = new JButton("Add");
    JButton edit = new JButton("Edit");
    JButton delete = new JButton("Delete");

    JLabel fNameLabel = new JLabel("First Name");
    JLabel lNameLabel = new JLabel("Last Name");
    JLabel nationalityLabel = new JLabel("Nationality");
    JLabel ageLabel = new JLabel("Age");
    JLabel netWorthLabel = new JLabel("Net Worth (mln.)");

    JTextField fNameTField = new JTextField();
    JTextField lNameTField = new JTextField();
    JTextField nationalityTField = new JTextField();
    JTextField ageTField = new JTextField();
    JTextField netWorthTField = new JTextField();

    JButton add1 = new JButton("Add");
    JButton edit1 = new JButton("Edit");
    JButton delete1 = new JButton("Delete");

    JLabel nameLabel = new JLabel("Name");

    JTextField nameTField = new JTextField();

    JButton add2 = new JButton("Add");
    JButton edit2 = new JButton("Edit");
    JButton delete2 = new JButton("Delete");

    JLabel age = new JLabel("Enter age of director");
    JTextField ageTF = new JTextField();
    JButton sort = new JButton("Show");

    JLabel yearOfGameLabel = new JLabel("Move release year");
    JTextField yearT = new JTextField();
    JLabel typeILabel = new JLabel("Genre");
    JTextField typeT = new JTextField();
    JButton sort2 = new JButton("Show");

    JPanel query1UpPanel = new JPanel();
    JPanel query1MidPanel = new JPanel();
    JPanel query1DownPanel = new JPanel();

    JPanel query2UpPanel = new JPanel();
    JPanel query2MidPanel = new JPanel();
    JPanel query2DownPanel = new JPanel();

    public MyFrame() {
        this.setVisible(true);
        this.setSize(700, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(tabbedPane);
        tabbedPane.add("Movie", movie);
        tabbedPane.add("Director", director);
        tabbedPane.add("Genre", genre);
        tabbedPane.add("Search 1", query1);
        tabbedPane.add("Search 2", query2);

        getComboBox();
        getComboBoxDirectors();

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane jTabbedPane = (JTabbedPane) e.getSource();
                int index = jTabbedPane.getSelectedIndex();

                switch (index){
                    case 0:
                        getComboBox();
                        getComboBoxDirectors();
                }
            }
        });

        movie.setLayout(new GridLayout(3, 1));
        movie.add(upPanel);
        movie.add(midPanel);
        movie.add(downPanel);

        upPanel.setLayout(new GridLayout(4, 2));
        upPanel.add(typeLabel);
        upPanel.add(jComboBox);
        upPanel.add(creatorLabel);
        upPanel.add(jComboBox1);
        upPanel.add(titleLabel);
        upPanel.add(titleTField);
        upPanel.add(yearLabel);
        upPanel.add(yearTField);

        midPanel.add(add);
        add.addActionListener(new AddAction());
        midPanel.add(edit);
        edit.addActionListener(new EditActionMovies());
        midPanel.add(delete);
        delete.addActionListener(new DeleteActionMovies());

        downPanel.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(450, 140));
        table.setModel(DBConnection.getAllModel());

        director.setLayout(new GridLayout(3, 1));
        director.add(directorUpPanel);
        director.add(directorMidPanel);
        director.add(directorDownPanel);

        directorUpPanel.setLayout(new GridLayout(5, 2));
        directorUpPanel.add(fNameLabel);
        directorUpPanel.add(fNameTField);
        directorUpPanel.add(lNameLabel);
        directorUpPanel.add(lNameTField);
        directorUpPanel.add(nationalityLabel);
        directorUpPanel.add(nationalityTField);
        directorUpPanel.add(ageLabel);
        directorUpPanel.add(ageTField);
        directorUpPanel.add(netWorthLabel);
        directorUpPanel.add(netWorthTField);

        directorMidPanel.add(add1);
        add1.addActionListener(new AddActionDirectors());
        directorMidPanel.add(edit1);
        edit1.addActionListener((new EditActionDirector()));
        directorMidPanel.add(delete1);
        delete1.addActionListener(new DeleteActionDirector());

        directorDownPanel.add(jScrollPane1);
        jScrollPane1.setPreferredSize(new Dimension(450, 140));
        table1.setModel(DBConnection.getAllDirectors());

        genre.setLayout(new GridLayout(3, 1));
        genre.add(genreUpPanel);
        genre.add(genreMidPanel);
        genre.add(genreDownPanel);
        genreUpPanel.setLayout(new GridLayout(1, 2));

        genreUpPanel.add(nameLabel);
        genreUpPanel.add(nameTField);

        genreMidPanel.add(add2);
        add2.addActionListener(new AddActionGenre());
        genreMidPanel.add(edit2);
        edit2.addActionListener(new EditActionGenre());
        genreMidPanel.add(delete2);
        delete2.addActionListener(new DeleteActionGenre());

        genreDownPanel.add(jScrollPane2);
        jScrollPane2.setPreferredSize(new Dimension(450, 140));
        table2.setModel(DBConnection.getAllGenre());

        query1.setLayout(new GridLayout(3, 1));
        query1.add(query1UpPanel);
        query1.add(query1MidPanel);
        query1.add(query1DownPanel);

        query1UpPanel.setLayout(new GridLayout(1, 1));
        query1UpPanel.add(age);
        query1UpPanel.add(ageTF);

        query1MidPanel.add(sort);
        sort.addActionListener(new Sort1Action());

        query1DownPanel.add(jScrollQ);
        jScrollQ.setPreferredSize(new Dimension(450, 140));
        table1.setModel(DBConnection.getAllDirectors());

        query2.setLayout(new GridLayout(3, 1));
        query2.add(query2UpPanel);
        query2.add(query2MidPanel);
        query2.add(query2DownPanel);

        query2UpPanel.setLayout(new GridLayout(2, 1));
        query2UpPanel.add(yearOfGameLabel);
        query2UpPanel.add(yearT);
        query2UpPanel.add(typeILabel);
        query2UpPanel.add(typeT);

        query2MidPanel.add(sort2);
        sort2.addActionListener(new Sort2Action());

        query2DownPanel.add(getjScrollQ1);
        getjScrollQ1.setPreferredSize(new Dimension(450, 140));

        this.setVisible(true);
    }

    public void resetComplexSort(){
        yearT.setText("");;
        typeT.setText("");
    }

    public void resetSimpleSort() {
        ageTF.setText("");
    }

    public void resetMovies() {
        titleTField.setText("");
        typeTField.setText("");
        yearTField.setText("");
        creatorTField.setText("");
    }

    public void resetGenre() {
        nameTField.setText("");
    }

    public void resetDirector() {
        fNameTField.setText("");
        lNameTField.setText("");
        nationalityTField.setText("");
        ageTField.setText("");
        netWorthTField.setText("");
    }
    private void getComboBox(){
        connection = DBConnection.getConnected();
        try {
            String sql = "SELECT * FROM GENRE";
            state = connection.prepareStatement(sql);
            ResultSet result = null;
            result = state.executeQuery();

            jComboBox.removeAllItems();
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String item = "ID: " + id + " " + name;
                jComboBox.addItem(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void getComboBoxDirectors(){
        connection = DBConnection.getConnected();
        try {
            String sql = "SELECT * FROM DIRECTOR";
            state = connection.prepareStatement(sql);
            ResultSet result = null;
            result = state.executeQuery();

            jComboBox1.removeAllItems();
            while (result.next()) {
                int creatorId = result.getInt(1);
                String name = result.getString(2);
                String item = "ID " + creatorId + " " + name;
                jComboBox1.addItem(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    class Sort2Action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int year = 0;
            String type = typeT.getText();

            try{
                year = Integer.parseInt(yearT.getText());
            }catch (NumberFormatException er){
                yearT.setText("Enter only numbers");
            }
            Connection connection = null;
            connection = DBConnection.getConnected();
            String sql = "SELECT TITLE,YEAR, genre FROM GENRE G JOIN MOVIES M ON G.GENRE_ID = M.GENRE_ID WHERE YEAR="
                    + year + "AND genre='" + type + "';";
            tableQuery1.setModel(DBConnection.getComplexSorting(year, type));
            resetComplexSort();

        }
    }

    class Sort1Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int age = 0;

            try {

                age = Integer.parseInt(ageTF.getText());

            } catch (NumberFormatException ex) {

                ageTF.setText("Enter only numbers!");

            }

           connection = null;
            connection = DBConnection.getConnected();

            String sql = "select * from DIRECTOR where age=?";
            ResultSet result = null;
            MyModel model = null;

            try {
                state = connection.prepareStatement(sql);
                state.setInt(1, age);
                result = state.executeQuery();
                model = new MyModel(result);
                tableQuery.setModel(model);
                resetSimpleSort();

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }

    class AddAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String title = titleTField.getText();

            String genre = (String) jComboBox.getSelectedItem();
            String[] typeArray = genre.split(" ");
            int typeId = Integer.parseInt(typeArray[1]);

            int year = 0;

            String creator = (String) jComboBox1.getSelectedItem();
            String[] creatorArray = creator.split(" ");
            int creatorId = Integer.parseInt(creatorArray[1]);
            try {
                year = Integer.parseInt(yearTField.getText());

            } catch (NumberFormatException ex) {
                yearTField.setText("Only numbers!");
            }

            connection = DBConnection.getConnected();
            if (connection == null) {
                return;
            }
            String sql = "insert into MOVIES(genre_id, director_id, title, year) values (?,?,?,?);";

            try {state = connection.prepareStatement(sql);

                state.setInt(1, typeId);

                state.setInt(2, creatorId);
                state.setString(3, title);
                state.setInt(4, year);
                state.execute();
                table.setModel(DBConnection.getAllModel());
                resetMovies();

            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        }

    }

    class AddActionDirectors implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String fname = fNameTField.getText();
            String lname = lNameTField.getText();
            String nationality = nationalityTField.getText();
            int age = 0;
            float netWorth = 0.0f;

            try {

                age = Integer.parseInt(ageTField.getText());
                netWorth = Float.parseFloat(netWorthTField.getText());

            } catch (NumberFormatException ex) {

                ageTField.setText("Enter only numbers,please!");

            }

            connection = DBConnection.getConnected();
            if (connection == null) {
                return;
            }
            String sql = "insert into director(fname, lname, nationality, age, net_worth) values (?,?,?,?,?);";

            try {
                state = connection.prepareStatement(sql);

                state.setString(1, fname);
                state.setString(2, lname);
                state.setString(3, nationality);
                state.setInt(4, age);
                state.setFloat(5, netWorth);
                state.execute();
                table1.setModel(DBConnection.getAllDirectors());
                resetDirector();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        }

    }

    class AddActionGenre implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String name = nameTField.getText();

            connection = DBConnection.getConnected();
            if (connection == null) {
                return;
            }
            String sql = "insert into genre(genre) values (?);";

            try {
                state = connection.prepareStatement(sql);

                state.setString(1, name);

                state.execute();
                table2.setModel(DBConnection.getAllGenre());
                resetGenre();

            } catch (SQLException e1) {

                e1.printStackTrace();
            }

        }
    }

    class EditActionGenre implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String name = nameTField.getText();

            connection = DBConnection.getConnected();
            if (connection == null) {
                return;
            }

            String sql = "update genre set genre='" + name + "'" + "WHERE GENRE_ID ="
                    + table2.getValueAt(table2.getSelectedRow(), 0) + ";";

            try {
                state = connection.prepareStatement(sql);

                state.execute();
                table2.setModel(DBConnection.getAllGenre());
                resetGenre();

            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        }

    }

    class EditActionMovies implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String genre = (String) jComboBox.getSelectedItem();
            String[] typeArray = genre.split(" ");
            int typeId = Integer.parseInt(typeArray[1]);
            String creator = (String) jComboBox1.getSelectedItem();
            String[] creatorArray = creator.split(" ");
            int creatorId = Integer.parseInt(creatorArray[1]);
            String title = titleTField.getText();
            int year = 0;

            try {
                year = Integer.parseInt(yearTField.getText());

            } catch (NumberFormatException ex) {
                yearTField.setText("Enter only numbers!");
            }


            System.out.println("type: " + typeId);
            System.out.println("Creator: " + creatorId);
            System.out.println("title: " + title);
            System.out.println("year: " + year);
            connection = DBConnection.getConnected();
            if (connection == null) {
                return;
            }

            String sql = "update movies set genre_id='" + typeId + "',director_id='" +creatorId  + "',title='" + title
                    + "',year='" + year + "'WHERE movie_id='" + table.getValueAt(table.getSelectedRow(), 0) + "';";

            try {
               PreparedStatement state = connection.prepareStatement(sql);

                state.execute();
                table.setModel(DBConnection.getAllModel());
                resetMovies();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        }
    }

    class EditActionDirector implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String fname = fNameTField.getText();
            String lname = lNameTField.getText();
            String nationality = nationalityTField.getText();
            int age = 0;
            float netWorth = 0.0f;

            try {

                age = Integer.parseInt(ageTField.getText());
                netWorth = Float.parseFloat(netWorthTField.getText());

            } catch (NumberFormatException ex) {
                ageTField.setText("Enter only numbers,please!");
                netWorthTField.setText("Enter only numbers,please!");
            }

            connection = DBConnection.getConnected();
            if (connection == null) {
                return;
            }

            String sql = "update director set fname='" + fname + "',lname='" + lname + "',nationality='" + nationality
                    + "',age='" + age + "',net_worth='" + netWorth + "'WHERE director_id='"
                    + table1.getValueAt(table1.getSelectedRow(), 0) + "';";

            try {
               PreparedStatement state = connection.prepareStatement(sql);

                state.execute();
                table1.setModel(DBConnection.getAllDirectors());
                resetDirector();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }

        }
    }

    class DeleteActionMovies implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String name = (table.getValueAt(table.getSelectedRow(), 0)).toString();
            String sql = "DELETE FROM MOVIES " + "WHERE movie_id='" + name + "';";

            connection = DBConnection.getConnected();
            if (connection == null) {
                return;
            }

            try {
                state = connection.prepareStatement(sql);

                state.execute();
                table.setModel(DBConnection.getAllModel());

            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        }

    }

    class DeleteActionDirector implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String name = (table1.getValueAt(table1.getSelectedRow(), 0)).toString();
            String sql = "DELETE FROM director " + "WHERE director_id='" + name + "';";
            String sql2 = "DELETE FROM MOVIES " + "WHERE director_id='" + name + "';";
            connection = DBConnection.getConnected();
            if (connection == null) {
                return;
            }

            try {
                state = connection.prepareStatement(sql2);

                state.execute();
                table1.setModel(DBConnection.getAllDirectors());

            } catch (SQLException e1) {

                e1.printStackTrace();
            }
            try {
                state = connection.prepareStatement(sql);

            state.execute();
            table1.setModel(DBConnection.getAllDirectors());

            table.setModel(DBConnection.getAllModel());

        } catch (SQLException e1) {

            e1.printStackTrace();
        }
        }

    }

    class DeleteActionGenre implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String nameid = (table2.getValueAt(table2.getSelectedRow(), 0)).toString();

            String sql = "DELETE FROM genre WHERE genre_id= " + nameid + ";";

            connection = DBConnection.getConnected();
            if (connection == null) {
                return;
            }

            try {
                state = connection.prepareStatement(sql);

                state.execute();
                table2.setModel(DBConnection.getAllGenre());

            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        }
    }


}
