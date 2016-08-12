package com.fx.mt4TradeRecord.dao.impl;

import com.fx.RPQueryVolume.model.RPQueryVolume;
import com.fx.bbookuserReport.model.BbookuserReport;
import com.fx.mt4TradeRecord.dao.IMt4TradesDao;
import com.fx.mt4TradeRecord.model.Mt4Trades;
import com.fx.mt4TradeRecord.model.Mt4Volume;
import com.fx.util.Pagination;

import mybatis.framework.core.dao.BaseDao;
import mybatis.framework.util.DBContants;

import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class Mt4TradesDaoImpl extends BaseDao<Mt4Trades> implements IMt4TradesDao {

    public Mt4TradesDaoImpl() {
        super(DBContants.DB_MT4TRADE, IMt4TradesDao.class.getName());
    }

    @Override
    public List<Mt4Trades> getMt4TradesByAccount(int login, Date date1, Date date2) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("login", login);
        params.put("date1", date1);
        params.put("date2", date2);
        List<Mt4Trades> list = findList("queryListByAccount", params);
        return list;
    }

    @Override
    public List<Mt4Trades> getMt4TradesByAccountAndDate(Map<String, Object> params) {

        List<Mt4Trades> list = findList("getMt4TradesByAccountAndDate", params);
        return list;
    }

    @Override
    public List<Integer> findTradedLoginByDate(Map<String, Object> params) {

        List<Integer> list = findList("findTradedLoginByDate", params);
        return list;
    }

    @Override
    public List<Mt4Trades> queryByDateAndLogin(Map<String, Object> params) {

        List<Mt4Trades> list = findList("queryByDateAndLogin", params);
        return list;
    }

    @Override
    public List<Mt4Trades> queryByDateLogin(Map<String, Object> params) {

        List<Mt4Trades> list = findList("queryByDateLogin", params);
        return list;
    }

    public static void main(String[] args) throws ParseException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("date1", "2015-03-04 00:00:00");
        params.put("date2", "2015-03-06 00:00:00");
        params.put("login", 100262);
        String date1 = "2015-03-04 00:00:00";
        String date2 = "2015-03-07 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse(date1);
        Date d2 = sdf.parse(date2);
        Integer login = 100262;
        List<Mt4Trades> list = new Mt4TradesDaoImpl().getMt4TradesByAccount(login, d1, d2);
        System.out.println(list.size());
    }

    @Override
    public int queryCountByCondition(Map<String, Object> params) {
        return (Integer) findOne("queryCountByCondition", params);
    }

    @Override
    public List<Mt4Trades> queryByCondition(Map<String, Object> params) {
        return findList("queryByCondition", params);
    }

    @Override
    public int queryCountByConditionByIb(Map<String, Object> params) {
        return (Integer) findOne("queryCountByConditionByIb", params);
    }

    @Override
    public List<Mt4Trades> queryByConditionByIb(Map<String, Object> params) {
        return findList("queryByConditionByIb", params);
    }

    @Override
    public int queryCountByConditionByAccount(Map<String, Object> params) {
        return (Integer) findOne("queryCountByConditionByAccount", params);
    }

    @Override
    public List<Mt4Trades> queryByConditionByAccount(Map<String, Object> params) {
        return findList("queryByConditionByAccount", params);
    }

    @Override
    public int queryCountByConditionByAccountHistory(Map<String, Object> params) {
        return (Integer) findOne("queryCountByConditionByAccountHistory", params);
    }

    @Override
    public List<Mt4Trades> queryByConditionByAccountHistory(
            Map<String, Object> params) {
        return findList("queryByConditionByAccountHistory", params);
    }

    @Override
    public int queryCountByConditionByIbHistory(Map<String, Object> params) {
        return (Integer) findOne("queryCountByConditionByIbHistory", params);
    }

    @Override
    public List<Mt4Trades> queryByConditionByIbHistory(
            Map<String, Object> params) {
        return findList("queryByConditionByIbHistory", params);
    }

    @Override
    public List<Mt4Trades> queryBymt4Type(int mt4Type, String interviewDate1, String interviewDate2,String login,List loginlist) {
        Map map = new HashMap();
        map.put("mt4Type", mt4Type);
        if(loginlist!=null && loginlist.size()>0){
            map.put("loginlist", loginlist);
        }
        if (interviewDate1!=null&&interviewDate2!=null&&interviewDate1 != "" && interviewDate2 != "" && interviewDate1.length() > 0 && interviewDate2.length() > 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                try {
                    Date date1 = simpleDateFormat.parse(interviewDate1);
                    Date date2 = simpleDateFormat3.parse(interviewDate2+" 23:59:59");
                    map.put("date1", date1);
                    map.put("date2", date2);
                } catch (Exception e) {
            }

        } else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                Date date = new Date();
                String time1 = simpleDateFormat.format(date)+"/01";
                String time2 = simpleDateFormat2.format(date)+" 23:59:59";
                Date date1 = simpleDateFormat.parse(time1);
                Date date2 = simpleDateFormat3.parse(time2);
                map.put("date1", date1);
                map.put("date2", date2);
            } catch (Exception e) {
            }
        }
        if(login!=""&&login!=null){
            map.put("login",login);
        }
        int pagenum =1 ;
        map.put("pagenum",pagenum);
        List<Mt4Trades> mt4Trades = new ArrayList<Mt4Trades>();
        if (mt4Type == 1) {
            mt4Trades = findList("queryBytype1", map);
        } else if (mt4Type == 2) {
            mt4Trades = findList("queryBytype2", map);
        } else if (mt4Type == 3) {
            mt4Trades = findList("queryBytype3", map);
        } else if (mt4Type == 4) {
            mt4Trades = findList("queryBytype4", map);
        } else if (mt4Type == 5) {
            mt4Trades = findList("queryBytype5", map);
        }
        return mt4Trades;
    }

    @Override
    public List<Mt4Trades> getByTimeAccount(List<Integer> login, String interviewDate1, String interviewDate2) {
        Map map = new HashMap();
        if (interviewDate1!=null&&interviewDate2!=null&&interviewDate1 != "" && interviewDate2 != "" && interviewDate1.length() > 0 && interviewDate2.length() > 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                Date date1 = simpleDateFormat.parse(interviewDate1);
                Date date2 = simpleDateFormat3.parse(interviewDate2+" 23:59:59");
                map.put("date1", date1);
                map.put("date2", date2);
            } catch (Exception e) {
            }

        } else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                Date date = new Date();
                String time1 = simpleDateFormat.format(date)+"/01";
                String time2 = simpleDateFormat2.format(date)+" 23:59:59";
                Date date1 = simpleDateFormat.parse(time1);
                Date date2 = simpleDateFormat3.parse(time2);
                map.put("date1", date1);
                map.put("date2", date2);
            } catch (Exception e) {
            }
        }
        if(login!=null && login.size()>0){
            map.put("loginList",login);
        }
        List<Mt4Trades> mt4Trades = new ArrayList<Mt4Trades>();
        mt4Trades = findList("getByTimeAccount", map);
        return mt4Trades;
    }

    @Override
    public int countByPositions(Map params) {
        return (Integer)findOne("countByPositions",params);
    }

    @Override
    public List<RPQueryVolume> queryVolumnList(Map map) {
        return findList("queryVolumnList",map);
    }

    @Override
    public RPQueryVolume queryVolumnSum(Map map) {
        return (RPQueryVolume)findOne("queryVolumnSum",map);
    }

    @Override
    public List<Mt4Trades> getMt4TradesByCloseTime(Date begin, Date end) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("date1", begin);
        params.put("date2", end);
        List<Mt4Trades> list = findList("getMt4TradesByDate", params);
        return list;
    }

    @Override
    public List<Mt4Trades> findTradesByPage(Pagination pagination, Map<String, Object> params) {
        return super.findList("findByPageCondition", params);
    }

    @Override
    public List<Mt4Trades> findCommissionDetail(int login) {
        Map map = new HashMap();
        map.put("login",login);
        return findList("findCommissionDetail", map);
    }

	@Override
	public int queryCountByConditionGroupBySymbol(Map<String, Object> params) {
		 return (Integer) findOne("queryCountByConditionGroupBySymbol", params);
	}

	@Override
	public List<Mt4Trades> pageQueryByConditionGroupBySymbol(
			Map<String, Object> params) {
		return super.findList("pageQueryByConditionGroupBySymbol", params);
	}

	@Override
	public int queryCountByConditionMt4AccountDetailBySymbo(
			Map<String, Object> params) {
		return (Integer) findOne("queryCountByConditionMt4AccountDetailBySymbo", params);
	}

	@Override
	public List<Mt4Trades> pageQueryByConditionMt4AccountDetailBySymbol(
			Map<String, Object> params) {
		return super.findList("pageQueryByConditionMt4AccountDetailBySymbol", params);
	}

    @Override
    public List<Mt4Trades> queryByTimeLogin(List<Integer> login, String interviewDate1, String interviewDate2) {
        Map map = new HashMap();
        if (interviewDate1!=null&&interviewDate2!=null&&interviewDate1 != "" && interviewDate2 != "" && interviewDate1.length() > 0 && interviewDate2.length() > 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                Date date1 = simpleDateFormat.parse(interviewDate1);
                Date date2 = simpleDateFormat3.parse(interviewDate2+" 23:59:59");
                map.put("date1", date1);
                map.put("date2", date2);
            } catch (Exception e) {
            }

        } else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                Date date = new Date();
                String time1 = simpleDateFormat.format(date)+"/01";
                String time2 = simpleDateFormat2.format(date)+" 23:59:59";
                Date date1 = simpleDateFormat.parse(time1);
                Date date2 = simpleDateFormat3.parse(time2);
                map.put("date1", date1);
                map.put("date2", date2);
            } catch (Exception e) {
            }
        }
        if(login!=null && login.size()>0){
            map.put("loginList",login);
        }
        List<Mt4Trades> mt4Trades = new ArrayList<Mt4Trades>();
        mt4Trades = findList("queryByTimeLogin", map);
        return mt4Trades;
    }

    @Override
    public List<BbookuserReport> selectBbookProfit(Map map) {
        return findList("selectBbookProfit",map);
    }

}