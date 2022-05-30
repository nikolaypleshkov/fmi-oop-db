import javax.swing.table.AbstractTableModel;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class MyModel extends AbstractTableModel {
    private static final long serialUID = 1L;
    private ResultSet result;
    private int rowCount;
    private int columnCount;
    private ArrayList<Object> data = new ArrayList<Object>();

    public MyModel(ResultSet res) throws Exception
    {
        setRS(res);
    }

    public void setRS(ResultSet res) throws Exception{
        this.result = res;
        ResultSetMetaData resultSetMetaData = res.getMetaData();
        rowCount = 0;
        columnCount = resultSetMetaData.getColumnCount();

        while(res.next()){
            Object[] row = new Object[columnCount];
            for(int i = 0, len = columnCount; i < len; i++){
                row[i] = res.getObject(i + 1);
            }
            data.add(row);
            rowCount++;
        }
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] row = (Object[])  data.get(rowIndex);
        return row[columnIndex];
    }

    public String getColumnName(int columnIndex){
        try{
            ResultSetMetaData resultSetMetaData = result.getMetaData();
            return resultSetMetaData.getColumnName(columnIndex + 1);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
