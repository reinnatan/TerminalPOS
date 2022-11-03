package service;

public interface ReportActions {
    void monthlyReport(int month, int year) throws Exception;
    void yearReport(int year) throws Exception;
    void monthlyProfit(int month, int year) throws Exception;
    void yearProfit(int year) throws Exception;
}
