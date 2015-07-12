package org.example.domain;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import org.example.ExampleBaseTestCase;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ParentRawSqlTest extends ExampleBaseTestCase {

    @Test
    public void RawSqlParentLoad() {
        List<Data> exampleData = new ArrayList<>();
        exampleData.add(new Data(0));
        exampleData.add(new Data(1));
        exampleData.add(new Data(2));

        ChildA a = new ChildA(0);
        a.setData(exampleData);
        Ebean.save(a);

        ChildB b = new ChildB(1);
        b.setData(exampleData);
        Ebean.save(b);

        ChildA c = new ChildA(2);
        c.setData(exampleData);
        Ebean.save(c);

        RawSql rawSql = RawSqlBuilder
                .parse("select type, id, number from parent where number > 1")
                .create();
        List<Parent> partial = Ebean.find(Parent.class).setRawSql(rawSql)
                .findList();
        assertNotNull(partial.get(0).getData());
        assertEquals(partial.get(0).getData().get(0).getNumber().intValue(),0);
    }

    @Test
    public void RawSqlParentFetch() {
        List<Data> exampleData = new ArrayList<>();
        exampleData.add(new Data(0));
        exampleData.add(new Data(1));
        exampleData.add(new Data(2));

        ChildA a = new ChildA(0);
        a.setData(exampleData);
        Ebean.save(a);

        ChildB b = new ChildB(1);
        b.setData(exampleData);
        Ebean.save(b);

        ChildA c = new ChildA(2);
        c.setData(exampleData);
        Ebean.save(c);

        RawSql rawSql = RawSqlBuilder
                .unparsed("select type, id from parent where number > 1")
                .columnMappingIgnore("type")
                .columnMapping("id", "parent.id")
                .create();
        List<ParentAggregate> aggregates = Ebean.find(ParentAggregate.class).setRawSql(rawSql)
                .fetch("parent", new FetchConfig().query())
                .findList();
        List<Parent> partial = new ArrayList<>();
        for(ParentAggregate aggregate: aggregates) {
            partial.add(aggregate.parent);
        }
        assertNotNull(partial.get(0).getData());
        assertEquals(partial.get(0).getData().get(0).getNumber().intValue(),0);
    }

}
