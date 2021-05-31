package com.example.tobyspring.learningtest.spring.oxm;

import com.example.tobyspring.user.sqlservice.jaxb.SqlType;
import com.example.tobyspring.user.sqlservice.jaxb.Sqlmap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/OxmTest-context.xml")
class OxmTest {
    @Autowired
    Unmarshaller unmarshaller;

    @Test
    public void unmarshallSqlMap() throws IOException {
        Source xmlSource = new StreamSource(getClass().getResourceAsStream("/test-sqlmap.xml"));

        Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(xmlSource);

        List<SqlType> sqlList = sqlmap.getSql();

        assertThat(sqlList.size()).isEqualTo(3);
        assertThat(sqlList.get(0).getKey()).isEqualTo("add");
        assertThat(sqlList.get(0).getValue()).isEqualTo("insert");
        assertThat(sqlList.get(1).getKey()).isEqualTo("get");
        assertThat(sqlList.get(1).getValue()).isEqualTo("select");
        assertThat(sqlList.get(2).getKey()).isEqualTo("delete");
        assertThat(sqlList.get(2).getValue()).isEqualTo("delete");
    }
}
