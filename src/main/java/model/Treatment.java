package model;

import utils.DateConverter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Treatments are the work the caregivers do to the patients.
 */
public class Treatment {
    private long tid;
    private long pid;
    private long cid;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;
    private boolean shown;

    /**
     * constructs a treatment from the given params.
     * @param pid
     * @param cid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     * @param shown
     */
    public Treatment(long pid, long cid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks, boolean shown) {
        this.pid = pid;
        this.cid = cid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.shown = shown;
    }

    /**
     * constructs a treatment from the given params.
     * @param tid
     * @param pid
     * @param cid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     * @param shown
     */
    public Treatment(long tid, long pid, long cid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks, boolean shown) {
        this.tid = tid;
        this.pid = pid;
        this.cid = cid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.shown = shown;
    }

    /**
     *
     * @return treatment id
     */
    public long getTid() {
        return tid;
    }

    /**
     *
     * @return patient id
     */
    public long getPid() {
        return this.pid;
    }

    /**
     *
     * @return caregiver id
     */
    public long getCid() {
        return this.cid;
    }

    /**
     * @return date as a String
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * @return begin time of the treatment as a String
     */
    public String getBegin() {
        return begin.toString();
    }

    /**
     * @return end time of the treatment as a String
     */
    public String getEnd() {
        return end.toString();
    }

    /**
     * convert given param to a localDate and store as new <code>date</code>
     * @param s_date as string
     */
    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }

    /**
     * convert given param to a localDate and store as new <code>begin</code>
     * @param begin as string
     */
    public void setBegin(String begin) {
        LocalTime time = DateConverter.convertStringToLocalTime(begin);
        this.begin = time;
    }

    /**
     * convert given param to a localDate and store as new <code>end</code>
     * @param end as string
     */
    public void setEnd(String end) {
        LocalTime time = DateConverter.convertStringToLocalTime(end);
        this.end = time;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks new remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return shown
     */
    public boolean isShown() {
        return shown;
    }

    /**
     * @param shown new shown
     */
    public void setShown(boolean shown) {
        this.shown = shown;
    }

    /**
     * @return string-representation of the treatment
     */
    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks + "\n";
    }
}