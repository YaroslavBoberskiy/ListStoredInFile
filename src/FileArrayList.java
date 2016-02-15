import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by YB on 14.02.2016.
 */
public class FileArrayList implements SimpleList, Iterable<String> {

    private File file;
    private ArrayList<String> list = new ArrayList<String>();

    FileArrayList() {
        file = new File("C:\\Users\\YB\\Desktop\\ListStoredInFile\\resources\\listObjectsStorage");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        restoreList();
    }


    @Override
    public void add(Object object) {
        list.add(object.toString());
        updateFile();
    }

    @Override
    public void add(int index, Object object) {
        list.add(index, object.toString());
        updateFile();
    }

    @Override
    public String get(int idx) {
        if (list.get(idx) != null) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                String line;
                int lineCounter = 0;
                while ((line = br.readLine()) != null) {
                    if (lineCounter == idx) {
                        return line;
                    }
                    lineCounter++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void remove(Object object) {
        list.remove(object.toString());
        updateFile();
    }

    @Override
    public void remove(int idx) {
        list.remove(idx);
        updateFile();
    }


    @Override
    public void clear() {
        list.clear();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    private void restoreList() {
        list.clear();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFile() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < list.size(); i++) {
                fileWriter.write(list.get(i).toString() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterator<String> iterator() {
        final Object[] array = list.toArray();
        Iterator<String> it = new Iterator<String>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return (currentIndex < array.length) && array[currentIndex] != null;
            }

            @Override
            public String next() {
                return String.valueOf(array[currentIndex++]);
            }
        };
        return it;
    }
}
