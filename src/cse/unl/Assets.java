//separate classes for each type of asset?
package cse.unl;

//should we use implement to have an owned to connect to person class
public abstract class Asset implements Owned{
  protected String code; //account code or owner code?
  protected String assetType;
  protected String label;


}

//need to check assetType from file and construct appropriate child class
class DepositAccount extends Asset {
  private double apr;
}

class PrivateInvestment extends Asset {
  //should these be doubles?
  private double quarterlyDividend;
  private double baseRateOfReturn;
  private double baseOmegaMeasure;
  private double totalValue;
}

class Stock extends Asset {
  private double quarterlyDividend;
  private double baseRateOfReturn;
  private double betaMeasure;
  private double stockSymbol;
  private double sharePrice;
}
