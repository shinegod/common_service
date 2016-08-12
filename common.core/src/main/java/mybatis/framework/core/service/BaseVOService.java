package mybatis.framework.core.service;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fx.util.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import mybatis.framework.core.model.IValueObject;
import mybatis.framework.core.support.Page;


/**
 * Service接口抽象实现
 * 

 * @param <V>
 */
public abstract class BaseVOService<V extends IValueObject> extends BaseService<V> implements
		IValueObjectService<V>
{

	public BaseVOService()
	{
	}

	@Override
	public V findById(Serializable id)
	{
		return getDao().findById("selectByPrimaryKey", id);
	}

	@Override
	public List<V> findAll()
	{
		return getDao().findAll("selectAll");
	}

	@Override
	public int doDeleteById(Serializable id)
	{
		return getDao().doDelete("deleteByPrimaryKey", id);
	}

	@Override
	public int doInsert(V vo)
	{

		return getDao().doInsert("insert", vo);
	};

	@Override
	public int doUpdateById(V vo)
	{

		return getDao().doUpdate("updateByPrimaryKey", vo);
	};
	
	@Override
	public Page pagedQuery(int pageNo, int pageSize, Map<String, Object> parameter)
	{
		return getDao().pagedQuery("pagedQuery", pageNo, pageSize, parameter);
	}

	@Override
	public int doInsertList(List<V> list)
	{
		return getDao().doInsertList("insertList", list);
	}
	
	protected static final String dateFormat = Config.getDateFormatDatetime();
	protected static final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
		public Date deserialize(JsonElement json, Type typeOfT,
								JsonDeserializationContext context)
				throws JsonParseException {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			String dateStr = json.getAsString();
			try {
				return format.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}
	}).serializeNulls().setDateFormat(dateFormat).create();
}
