import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class form extends JFrame{
    private JTextField textSearch;
    private JPanel panel1;
    private JButton displayCount;
    private JTable table1;
    private JPanel panel2;
    private JComboBox<String> comboBox1;
    private JButton sortT;
    private JButton searchB;
    private JButton addMovie;
    private JPanel panel3;
    private JScrollPane scrollpane;
    private JButton sortG;

    private List<Movie> movies = new ArrayList<>();;

    public form(List movies) {
        this.movies = movies;

        comboBox1.addItem("Action");comboBox1.addItem("Animation");comboBox1.addItem("Comedy");comboBox1.addItem("Horror");comboBox1.addItem("Romance");comboBox1.addItem("Thriller");
        populateTable();

        setContentPane(panel1);
        setSize(500,500);
        setVisible(true);

        displayCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Number of movies: " + movies.size());
            }
        });

        searchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)table1.getModel();
                String search = textSearch.getText().toLowerCase();
                boolean found = false;
                if (!textSearch.getText().isEmpty()) {
                    for(int i=0;i<model.getRowCount();i++){
                        if(model.getValueAt(i,0).toString().toLowerCase().contains(search)){
                            table1.setRowSelectionInterval(i,i);
                            table1.scrollRectToVisible(table1.getCellRect(i,0,true));
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        JOptionPane.showMessageDialog(null,"Movie not found.");
                    }
                    textSearch.setText("");
                } else {
                    JOptionPane.showMessageDialog(null,"Please enter a movie title.");
                }
            }
        });

        sortT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(movies,Comparator.comparing(Movie::getTitle,String.CASE_INSENSITIVE_ORDER));
                populateTable();
                // I really wanted to do this with a treeset for some reason but then realized it won't work for the genre button
                // since it doesn't allow duplicates so it was just wasting lines here :(, but it does work with a treeset by clearing
                // the movies list and adding the treeset back
            }
        });

        sortG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(movies,Comparator.comparing(Movie::getGenre));
                populateTable();
            }
        });

        addMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textSearch.getText().isEmpty()) {
                    movies.add(new Movie(textSearch.getText(),(String)comboBox1.getSelectedItem()));
                    populateTable();
                    textSearch.setText("");
                } else {
                    JOptionPane.showMessageDialog(null,"Please enter a movie title.");
                }
            }
        });
    }

    private void populateTable(){
        String[] columnNames = {"Title", "Genre"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table1.setModel(model);

        model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        for (Movie movie : movies) {
            Object[] row = {movie.getTitle(), movie.getGenre()};
            model.addRow(row);
        }
    }
}