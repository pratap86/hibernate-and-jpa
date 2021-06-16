# Hibernate-with-JPA

### Index
- Associations OR Relationship
- Inheritance Hierarchies
- Queries
  - Criteria Query
  - Named Query
  - Native Query
- Transaction Management
- Hibernate Caching Mechanism
- Hibernate soft delete
- Embedded & Embeddable
- Be cautious with toString method implementations
- Eager VS Lazy fetch
- Avoid N+1 problems
- Composite Primary Key
- Calling Stored Procedure
- Save & Retrieve Files(BLOB - Binary Large Object)
- Connecting to multiple databases

### Query
#### SQL - Queries from Tables
- SELECT * FROM COURSE;
#### JPQL - Queries from Entities
- Select c from Course c;

#### JPA uses the EntityManager for talk to Persistence Context
#### Hibernate uses the Seesion for talk to SessionFactory
#### @Transactional
 - Hibernate wait untill the last possible moment of change, if something is failed in between whole transaction would be rollback, except untill you not triggered EntityManager.flush().

``` ruby
@Transactional
public List<Comments> someReadOnlyMethod(){
  User user = EntityManager.find(User.class, 1000L);// By default transaction is associated with EntityManager and its done once find() method completed.
  List<Comment> comments = user.getComments();//triggered the SELECT query from DB, so DB connection is mandatory
}

```

#### @DirtiesContext
- use along with @Test, to reset the data in in-memory db in case of update/new record.
- To leave the data in consistent state

#### Caching
- First level cache is bounded with boundary of Transaction.
- Transaction boudary should be start from Service layer.

#### Soft Delete
``` ruby
// EntityManager.remove(Entity) remove complete row from DB where id = ?
// Hibernate Soft delete maintain history through 
@SQLDelete(sql = "update Course set is_deleted=true where id=?")
@Where(clause = "is_deleted=false")
public class Course {

private boolean isDeleted;

@PreRemove//in case of cacheable Entity would be updated
    public void preRemove(){
        this.isDeleted=true;
    }
}
```

