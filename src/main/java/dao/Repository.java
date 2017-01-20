package dao;

import domain.Entity;
import domain.IHaveId;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.mapper.IMapResultSetToEntity;
import dao.repository.IRepository;
import dao.uow.IUnitOfWork;
import dao.uow.IUnitOfWorkRepository;

public abstract class Repository <TEntity extends IHaveId> implements IRepository<TEntity>, IUnitOfWorkRepository{
    
	
	protected IUnitOfWork uow;
    protected Connection connection;
    protected Statement createTable;
    protected PreparedStatement selectById;
    protected PreparedStatement insert;
    protected PreparedStatement selectAll;
    protected PreparedStatement delete;
    protected PreparedStatement update;
    protected PreparedStatement list;
    protected IMapResultSetToEntity<TEntity> mapper;


    protected String selecteByIdSql= "SELECT * FROM " + getTableName() + " WHERE id=?";
    protected String deleteSql = "DELETE FROM " + getTableName() + " WHERE id=?";
    protected String selectAllSql = "SELECT * FROM " + getTableName() ;

    public Repository(IUnitOfWork uow,IMapResultSetToEntity<TEntity> mapper, Connection connection) {
        
        try{
        	this.uow = uow;
            this.connection = connection;
            this.mapper = mapper;
            createTable = connection.createStatement();
              createTableIfNotExist();
              selectById = connection.prepareStatement(selecteByIdSql);
              selectAll = connection.prepareStatement(selectAllSql);
              insert = connection.prepareStatement(insertSql());
              delete = connection.prepareStatement(deleteSql);
              update = connection.prepareStatement(updateSql());

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
    } 
   
	public void save(TEntity entity)
	{
		uow.markAsNew((Entity) entity,this);
	}


	public void update(TEntity entity)
	{
		uow.markAsChanged((Entity)entity, this);
	}


	public void delete(TEntity entity)
	{
		uow.markAsDeleted((Entity)entity, this);
	}
	
    private void createTableIfNotExist()
        {
            try {
                Statement createTable = this.connection.createStatement();
                boolean tableExists = false;

                ResultSet rs = connection.getMetaData().getTables(null, null, null, null);

                while (rs.next()) {
                    if (rs.getString("Table_Name").equalsIgnoreCase(getTableName())) {
                        tableExists = true;
                        break;
                    }
                }
                if (!tableExists)
                    createTable.executeUpdate(createTableSql());
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }
   
   public void persistUpdate(Entity entity) {
    	try {
			setUpdate((TEntity)entity);
			update.executeUpdate();
 		} catch (SQLException ex) {
			ex.printStackTrace();
		}
    }
 
  
    public void persistAdd(Entity entity) {
    	try {
			setInsert((TEntity)entity);
			insert.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

    }

    public void persistDelete(Entity entity) {
    	try {
			delete.setInt(1, entity.getId());
			delete.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
    }
    public List<TEntity> getAll() {
		List<TEntity> persons = new ArrayList<TEntity>();

		try {
			ResultSet rs = list.executeQuery();

			while (rs.next()) {
				persons.add(mapper.map(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}
    
    public TEntity get(int id) {

		try {
			selectById.setInt(1, id);
			ResultSet rs = selectById.executeQuery();
			rs.next();
			return mapper.map(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


    protected abstract String getTableName();
    protected abstract String insertSql();
    protected abstract String updateSql();
    protected abstract String createTableSql();
    protected abstract void setUpdate(TEntity entity) throws SQLException;
    protected abstract void setInsert(TEntity entity) throws SQLException;
}
