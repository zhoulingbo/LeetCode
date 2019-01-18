package pers.zhoulingbo.leetcode;

/**
 * 
 * 478. 在圆内随机生成点
 * 
 * @version v1.0.0 @author zhoulingbo 2019-1-18 新建与整理
 */
public class GenerateRandomPointInACircle
{
    private double radius;
    private double cx;
    private double cy;

    public GenerateRandomPointInACircle(double radius, double x_center, double y_center)
    {
        this.radius = radius;
        this.cx = x_center;
        this.cy = y_center;
    }

    public double[] randPoint()
    {
        double a = Math.random();
        double b = Math.random();

        double r = Math.sqrt(a) * radius;   // 如果不求开方则不会均匀分布
        double angle = 2 * Math.PI * b;

        return new double[] { r * Math.sin(angle) + cx, r * Math.cos(angle) + cy };
    }
}
