class Philosopher extends Thread {
  private int identity;
  private Fork leftFork;
  private Fork rightFork;
  private int scale;
  private Moderator moderator;

  Philosopher(int id, int s, Fork left, Fork right, Moderator m) {
    identity = id;
    scale = s;
    leftFork = left;
    rightFork = right;
    moderator = m;
  }

  public void run() {
    while (true) {
      // Thinking
      System.out.println("Philosopher " + identity + " is thinking");
      delay(scale);

      // Request permission from the moderator
      System.out.println("Philosopher " + identity + " requests permission to eat");
      moderator.requestPermission(identity, leftFork, rightFork);

      // Eating
      System.out.println("Philosopher " + identity + " is eating");
      delay(scale);

      // Release permission
      System.out.println("Philosopher " + identity + " has finished eating");
      moderator.releasePermission(identity, leftFork, rightFork);
    }
  }

  public void delay(int scale) {
    try {
      sleep((int) (Math.random() * scale));
    } catch (InterruptedException e) {
    }
  }
}