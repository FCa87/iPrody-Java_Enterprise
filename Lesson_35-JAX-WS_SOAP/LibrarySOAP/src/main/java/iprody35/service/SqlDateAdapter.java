package iprody35.service;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;

public class SqlDateAdapter extends XmlAdapter<String, Date> {

    // Из XML в Java (Получение)
    @Override
    public Date unmarshal(String input) throws Exception {
        if (input == null) return null;
        return Date.valueOf(input); // Ожидает формат yyyy-MM-dd
    }

    // Из Java в XML (Передача)
    @Override
    public String marshal(Date output) throws Exception {
        if (output == null) return null;
        return output.toString(); // java.sql.Date.toString() возвращает yyyy-MM-dd
    }
}
