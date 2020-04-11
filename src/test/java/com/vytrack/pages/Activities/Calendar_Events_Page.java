package com.vytrack.pages.Activities;

import com.automation.Pages.AbstractPageBase;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Calendar_Events_Page extends AbstractPageBase {

    @FindBy(css = "[title='Create Calendar event']")
    private WebElement createCalendarEvent;

    @FindBy(className = "select2-chosen")
    private WebElement owner;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    private WebElement startDate;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    private WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    private WebElement endTime;

    @FindBy(className = "grid-header-cell__label")
    private List<WebElement> columnNames;

    @FindBy(css = "iframe[id^='oro_calendar_event_form_description-uid']")
    private WebElement descriptionFrame;

    @FindBy(css = "[id^='oro_calendar_event_form_title-uid']")
    private WebElement title;

    @FindBy(id = "tinymce")
    private WebElement descriptionTextArea;

    @FindBy(css = "[class='btn-group pull-right'] > button")
    private WebElement saveAndClose;

    @FindBy(xpath = "(//div[@class='control-label'])[1]")
    private WebElement generalInfoTitle;

    @FindBy(xpath = "//label[text()='Description']/following-sibling::div//p[1]")
    private WebElement generalInfoDescription;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FindBy(xpath = "(//td[text()='Testers Meeting']//following-sibling::td//a[text()='...'])[2]")
    private WebElement threedotTesterMeetings;

    @FindBy(xpath = "//a[@title='Grid Settings']")
    private WebElement gridsetings;

    @FindBy(xpath = "//span[@class='close']")
    private WebElement closegridsettings;

    @FindBy(xpath = "//span[text()='Title']")
    private WebElement columnTitle;

    @FindBy(xpath = "//a[@class='btn-success btn dropdown-toggle']")
    private WebElement saveandcloseOptions;

    @FindBy(xpath = "//a[@title='Cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement allCalendarEvents;

    @FindBy(xpath = "//li[text()='9:00 PM']")
    private WebElement startTime_9PM;

    @FindBy (css = "[id^='oro_calendar_event_form_allDay']")
    private WebElement alldayevent;

    @FindBy(id = "recurrence-repeat-view147")
    private WebElement repeatcheckbox;

    @FindBy(css = "#recurrence-repeats-view147")
    private WebElement repeatsbox;

    public void enterCalendarEventTitle(String titleValue) {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(title)).sendKeys(titleValue);
    }

    public void enterCalendarEventDescription(String description) {
        //wait until frame is available and switch to it
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(descriptionFrame));
        descriptionTextArea.sendKeys(description);
        driver.switchTo().defaultContent();//exit from the frame
    }

    public void clickOnSaveAndClose() {
        wait.until(ExpectedConditions.elementToBeClickable(saveAndClose)).click();
    }

    public String getGeneralInfoTitleText() {
        BrowserUtils.waitForPageToLoad(20);
        return generalInfoTitle.getText();
    }

    public String getGeneralInfoDescriptionText() {
        BrowserUtils.waitForPageToLoad(20);
        return generalInfoDescription.getText();
    }

    //#############################################################
    public List<String> getColumnNames() {
        BrowserUtils.waitForPageToLoad(20);
        return BrowserUtils.getTextFromWebEelements(columnNames);
    }

    public String getStartTime() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']")));
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value");
    }

    public String getEndTime() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute("value");
    }

    public String getOwnerName() {
        BrowserUtils.waitForPageToLoad(20);
        //wait for element to be present in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select2-chosen")));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return owner.getText().trim();
    }

    public void clickToCreateCalendarEvent() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']")));
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEvent)).click();
        BrowserUtils.waitForPageToLoad(20);
    }

    public String getStartDate() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        BrowserUtils.scrollTo(startDate);
        return startDate.getAttribute("value");
    }

//################################### HOMEWORK ########################################################################

    public void hoverOverthreedots(){
       BrowserUtils.waitForPageToLoad(15);
        Actions actions=new Actions(Driver.getDriver());
        actions.moveToElement(threedotTesterMeetings);
    }


    public void clickGridSettings(){
        BrowserUtils.waitForPageToLoad(15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Grid Settings']")));
        wait.until(ExpectedConditions.elementToBeClickable(gridsetings)).click();
    }

    public void deselectTitle(){
        List<WebElement> clicks=Driver.getDriver().findElements(By.xpath("//td[@class='visibility-cell']"));
        for (int i = 1; i < clicks.size(); i++) {
            clicks.get(i).click();
        }
    }

    public void  closeGridSettings(){
        BrowserUtils.waitForPageToLoad(15);
        wait.until(ExpectedConditions.elementToBeClickable(closegridsettings)).click();
    }

    public boolean isTitledisplayed(){
        BrowserUtils.waitForPageToLoad(15);
        return columnTitle.isDisplayed();
    }

    public void clickSaveAndCloseOptions(){
        BrowserUtils.waitForPageToLoad(15);
        wait.until(ExpectedConditions.elementToBeClickable(saveandcloseOptions)).click();
    }

    public boolean optionIsDisplayed(){
        List<WebElement> options=Driver.getDriver().findElements(By.xpath("//li//button"));
       boolean eachdispalyed =true;
        for (WebElement each :options){
            if (!(each.isDisplayed())){
                eachdispalyed=false;
            }
        }
      return eachdispalyed;
    }


    public void clickCancelButton(){
      BrowserUtils.waitForPageToLoad(15);
       wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
       BrowserUtils.wait(2);
    }

    public String getallCalendarEvent(){
       return allCalendarEvents.getText();
    }


    public void select_09_00_PM(){
        BrowserUtils.waitForPageToLoad(15);
        BrowserUtils.clickWithJS(startTime_9PM);
//        BrowserUtils.scrollTo(startTime_9PM);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[text()='9:00 PM']"))).click();
//        select09.selectByVisibleText(startTime_9PM.getText());
    }


    public void selectAlldayEvent(){
        BrowserUtils.waitForPageToLoad(15);
        wait.until(ExpectedConditions.elementToBeClickable(alldayevent)).click();
        BrowserUtils.wait(2);
    }

    public boolean allDayEventisSelected(){
        return alldayevent.isSelected();
    }

    public boolean startTimeisDisplayed(){
        return startTime.isDisplayed();
    }

    public boolean endTimeisDisplayed(){
        return endTime.isDisplayed();
    }


    public void clickrepeatbox(){
        BrowserUtils.waitForPageToLoad(20);
        BrowserUtils.clickWithJS(repeatcheckbox);
        BrowserUtils.wait(2);
    }


    public String getrepaetsdefaultvalue(){
       return repeatcheckbox.getAttribute("value");
    }

    public List<String> selectrepaeatsbox(){
        Select select=new Select(repeatsbox);
        List<WebElement> repeatsboxoptions=select.getOptions();
        List<String > options=new ArrayList<>();
        for (WebElement each :repeatsboxoptions){
            options.add(each.getText());
        }

        return options;

    }

}
