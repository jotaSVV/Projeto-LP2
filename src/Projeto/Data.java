package Projeto;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Data implements Comparable<Data> {

  private int day;

  private int month;

  private int year;

  private int hour;

  private int minute;

  private int second;
  private int milisecond;
  public Data(int day, int year, int hour, int minute) {
    GregorianCalendar gregCalendar = new GregorianCalendar();
    this.month = gregCalendar.get(Calendar.MONTH) + 1;

    this.year = year;
    this.hour = hour;
    this.minute = minute;

    this.day = day;
  }


  public Data(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public Data() {
    GregorianCalendar gregCalendar = new GregorianCalendar();
    this.day = gregCalendar.get(Calendar.DAY_OF_MONTH);
    this.month = gregCalendar.get(Calendar.MONTH) + 1;
    this.year = gregCalendar.get(Calendar.YEAR);
    this.hour = gregCalendar.get(Calendar.HOUR_OF_DAY);
    this.minute = gregCalendar.get(Calendar.MINUTE);
    this.second = gregCalendar.get(Calendar.SECOND);
    this.milisecond = gregCalendar.get(Calendar.MILLISECOND);
  }


  public int compareTo(Data d) {
    if (this.year == d.year && this.month == d.month && this.day == d.day && this.hour == d.hour && this.minute == d.minute && this.second == d.second && this.milisecond == d.milisecond) {
      return 0;
    } else if (this.year == d.year) {
      if (this.month == d.month) {
        if (this.day == d.day) {
          if (this.hour == d.hour) {
            if (this.minute == d.minute) {
              if (this.second == d.second) {
                return this.milisecond < d.milisecond ? -1 : 1;
              }
              return this.second < d.second ? -1 : 1;
            }

            return this.minute < d.minute ? -1 : 1;
          }
          return this.hour < d.hour ? -1 : 1;
        }
        return this.day < d.day ? -1 : 1;
      } else {
        return this.month < d.month ? -1 : 1;
      }
    } else {
      return this.year < d.year ? -1 : 1;
    }
  }

  public int compareHorario(Data d) {
    if (this.day == d.day && this.hour == d.hour && this.minute == d.minute && this.second == d.second ) {
      return 0;
    }else if (this.day == d.day) {
          if (this.hour == d.hour) {
            if (this.minute == d.minute) {
              return this.second < d.second ? -1 : 1;
            }
            return this.minute < d.minute ? -1 : 1;
          }
          return this.hour < d.hour ? -1 : 1;
        }
        return this.day < d.day ? -1 : 1;
  }

  public boolean beforeHorario(Data d) {
    if(compareHorario(d) == -1){
      return true;
    }
    return false;
  }

  public boolean afterHorario(Data d) {
    if(compareHorario(d) == 1){
      return true;
    }
    return false;
  }

  public boolean beforeDate(Data d) {
    if(compareTo(d) == -1){
      return true;
    }
    return false;
  }

  public boolean afterDate(Data d) {
    if(compareTo(d) == 1){
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return this.day + "/" + this.month + "/" + this.year + " " + this.hour + ":" + this.minute + ":" + this.second + "." + this.milisecond;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  public int getSecond() {
    return second;
  }

  public void setSecond(int second) {
    this.second = second;
  }

  public int getMilisecond() {
    return milisecond;
  }

  public void setMilisecond(int milisecond) {
    this.milisecond = milisecond;
  }
}