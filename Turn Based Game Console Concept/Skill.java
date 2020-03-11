class Skill
{
    private String name;
    private int effect;
    private int energyReduce;

    Skill(String name, int effect, int energyReduce)
    {
        this.name = name;
        this.effect = effect;
        this.energyReduce = energyReduce;
    }

    String getName()
    {
        return name;
    }

    int getEffect()
    {
        return effect;
    }


    int getEnergyReduce()
    {
        return energyReduce;
    }

    void showSkillStat()
    {
        System.out.println("Name : " + name);
        System.out.println("Energy to use : " + energyReduce);
    }
}