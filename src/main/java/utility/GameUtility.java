package utility;

public class GameUtility {
  public static long getTimeInSeconds(long timePassed) {
    return (long)(timePassed / 1000_000_000.0);
  }

  public static long getTimeInMiliSeconds(long timePassed) {
    return (long)(timePassed / 1000_000.0);
  }
}
