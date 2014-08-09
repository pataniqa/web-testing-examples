web-testing-examples
--------------------

# Testing components

We determined you have three specific requirements:

- testing REST APIs
- testing web pages
- simplifying writing of tests.

## Testing REST APIs

Tools for writing REST APIs make it easy to do HTTP calls and provide ways of writing assertions about JSON or XML documents.

The [rest-assured](https://code.google.com/p/rest-assured/) tool you have already identified looks pretty good tool to do this kind of thing.  [Rest-Driver](https://github.com/rest-driver/rest-driver) developed by Nokia Music or [RESTFuse](http://developer.eclipsesource.com/restfuse/) do similar things. There are several blog posts about how to test REST APIs with Spock or Cumcumber-JVM. They use tools such as [JsonAssert](https://github.com/skyscreamer/JSONassert) although only solves part of the problem.

## Testing web pages

For testing web pages, people generally use [selenium](http://www.seleniumhq.org/) /  Web driver. However there are three problems with this tool:

- The Selenium browser plugins can be unreliable or difficult to configure on a continuous integration machine. Therefore people tend to prefer [HtmlUnit](http://htmlunit.sourceforge.net/), which is pure Java so runs fast, but isn't based on a real browser so has some strange behaviors, or [phantomjs](http://phantomjs.org/) which is based on Webkit but is a bit faster / more reliable than an embedded browser. The [PhantomJS Maven plugin](https://github.com/klieber/phantomjs-maven-plugin) makes deploying PhantomJS easier. PhantomJS is compatible with Selenium/Webdriver so you can still write code in the same way.
- The Selenium / Webdriver API is low level so writing tests takes time. So there are several different tools that try to improve on this e.g. [fluentlenium](https://github.com/FluentLenium/FluentLenium) or [selenide](http://selenide.org/). [geb](http://www.gebish.org/) which uses Groovy could also be put in this category. None of these tools seem to be perfect although these three are probably the most mature, although Geb is probably the least mature of the three. They are still not totally satisfactory because people are developing replacements like [Simplelenium](https://github.com/dgageot/simplelenium) - see the [introduction](http://blog.javabien.net/2014/04/15/simplelenium-writing-robust-tests-with-selenium/) - although they are still at the prototype stage.
 
- Sometimes the tests are written by non-coders who tend to use the [seleniumide](http://docs.seleniumhq.org/projects/ide/) tool. This is a plugin for Firefox that records someone driving a web browser, producing an Expect-like script in XML. This allows you to write tests quickly, but sometimes those tests are brittle and it can be hard to do large scale automation. The https://code.google.com/p/selunit/ project aims to let you run these scripts from JUnit /  Maven, so you can run them with CI. SauceLabs also have [Selenium Builder](https://saucelabs.com/builder) which is a better version of SeleniumIDE.

## Simplifying writing tests

Test code can get ugly quick, it has a maintenance cost and it can impact the speed of code development because badly designed tests mean changing the main code requires lots of test code changes. 

Although behavior driven design tools are touted as a different "behavior driven" approach to creating software, they can also help with simplifying the test code and making it more maintainable. 

There is a [nice post on Java ranch](http://www.coderanch.com/t/619414/design/BDD-Action-frameworks) that makes a distinction between higher level BDD tools such as [JBehave](http://jbehave.org/), [cucumber](http://cukes.info/) and[Cucumber-JVM](https://github.com/cucumber/cucumber-jvm), [EasyB](http://easyb.org/) and [Thucydides](http://www.thucydides.info/) and TDD-style tools such as [Spock](http://code.google.com/p/spock) or [Specs2](http://etorreborre.github.io/specs2/). 

[Testatoo](http://www.testatoo.org/) is a high level BDD tool written in Java that is designed explicitly to write tests for Selenium / Webdriver. 

There are blog posts with examples of using both Cucumber-JVM and Spock to test REST apis and REST websites. I have created some simple examples of using Cucumber-JVM with Rest-Assured and with Selenium / PhantomJS.

## Other languages

If you are prepared to go with non-JVM based solutions then there are possibilites such as:

- [robot framework](https://github.com/robotframework/robotframework) Testing framework normally used with Python although you can use it with other languages. Companies like Nokia and Verisign use this framework.
- [Jasmine](http://jasmine.github.io/) Popular BDD framework for Javascript.
- [frisby.js](http://frisbyjs.com/) A node.js based framework for testing REST APIs that uses jasmine.
- [cucumber](http://cukes.info/)

# Useful patterns

Here are two patterns that might be relevant to the testing REST APIs and web page tasks:

## Golden files

One pattern I used a lot in web crawling and data extraction framework I wrote I used "golden files" for test cases. 

Here when testing an operation - in my case performs data extraction, in your case call a web API - I have files that record the input and output. For each test, the test runner runs the test, but if a canonical result (the "golden file") does not exist it, it saves the output as a "candidate" golden file. Then the tester can inspect the output, check it is correct. Once the output is accepted, it is committed to source control. This is primarily a way of checking further changes don't cause regressions. 

Obviously this isn't as flexible as some other approaches, but it is possible to write a lot of test cases quickly using the pattern. Also I like being able to "eyeball" the expected result - sometimes that's easier then looking at some test code and having to remember what it is testing for.

## Database fixtures

Another pattern is we were talking about testing the deployed app. If the deployed app is a live service, then the data is changing. A common idea in the ROR world is a fixture which means sample data. So it might be helpful to deploy a version of the latest code against a fixture. This can might make testing easier and more repeatable. It also lets you tear down the fixture and repopulate the data at the end of the run.

# Comparisons

There are a few blog posts that compare different frameworks:

- Spock plus Geb versus the Robot Framework: http://stackoverflow.com/questions/16479539/spock-geb-vs-robot-framework
- rest-assured versus Jersey-Test-Framework: http://www.hascode.com/2011/09/rest-assured-vs-jersey-test-framework-testing-your-restful-web-services/

# Examples

I have put together some simple "hello-world" examples for

- [Testing the Youtube webservice using rest-assured](https://github.com/pataniqa/web-testing-examples/tree/master/rest-assured).
- [Testing Google.com using PhantomJS and the basic Selenium / Webdriver API](https://github.com/pataniqa/web-testing-examples/tree/master/phantomjs).
- [Testing Bing.com using Fluentlenium, a wrapper around Selenium / Webdriver](https://github.com/pataniqa/web-testing-examples/tree/master/fluentlenium).
- [Testing the Youtube API using Cucumber-JVM / rest-assured](https://github.com/pataniqa/web-testing-examples/tree/master/cucumber).
- [Testing the Bing.com using Cucumber-JVM, PhantomJS, Selenium / Webdriver API](https://github.com/pataniqa/web-testing-examples/tree/master/cucumber).

So clearly it is possible to combine these different tools together.

I have also collected links to different articles about these tools and other tools mentioned above in the README.md files in the subdirectories. 
