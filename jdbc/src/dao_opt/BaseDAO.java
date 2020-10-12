package dao_opt;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import util_opt.DBUtils;

/**
 * 封装对数据库中表的操作
 * @author huzihao
 * @since 2020/10/11 21:59
 */
public abstract class BaseDAO<T> {
    private final Class<T> aClass;

    {
        var parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        var actualTypeArgs = parameterizedType.getActualTypeArguments();
        aClass = (Class<T>) actualTypeArgs[0];
    }

    public int update(Connection connection, String sql, List<Object> holderValues) {
        try (var stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < holderValues.size(); i++) {
                stmt.setObject(i + 1, holderValues.get(i));
            }
            return stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public Optional<List<T>> getList(Connection connection, String sql, List<Object> holderValues) {
        return DBUtils.query(connection, aClass, sql, holderValues);
    }

    public T get(Connection connection, String sql, List<Object> holderValues) {
        var list = DBUtils.query(connection, aClass, sql, holderValues).orElse(null);
        return null == list ? null : list.get(0);
    }

    public Object getValue(Connection connection, String sql, List<Object> holderValues) {
        try (var stmt = connection.prepareStatement(sql)) {
            for (var i = 0; null != holderValues && i < holderValues.size(); i++) {
                stmt.setObject(i + 1, holderValues.get(i));
            }
            try (var resultSet = stmt.executeQuery()) {
                if (resultSet.next()) return resultSet.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
