# neoServices
 
 ## System Architecture
 
 ![144061535-7a42e85b-59d6-4f7f-9c35-18a48b49e6de](https://user-images.githubusercontent.com/75577090/172055547-1ce12b41-507f-4bbb-b10b-2ece02398697.png)
 
 ## Load Balancers [LB]

  ### Facts to be considered:

  - TLS : Determining the TLS at external LB or individual internal LB ? 
  - Certificate Management
  - Authentication : Where the authentication will happend ? Will it be on external LB or individual internal LB ?
  - High Availability [HA] :  will this application up for 24/7 ? Cross Region or Multi Region ?
  - Logging
  - Caching
  - Path Based Routing

  For production it could be vital to choose managed LB instead of managing own LBs. This is because if external LB died , then this will be most critical incident. For this we could let cloud providers to manage the external LB , and we can focus our own microservices. Popular cloud providers are Google cloud, AWS Elastic Load Balancing ( Application Load Balancer, Gateway Load Balancer, Network Load Balancer ).

  ### LB Algorithm :
  - Round Robin : Requests are distributed across the group servers sequentially.
  - Least Connections : Requests are sent to the server with the fewest current connections to clients.
  - Least Time : Requests are sent to the server selected by formula that combines the fastest response time  and fewest active connections. (Exclusive to NGINX Plus).
  - Hash : Requests are distributed across the group servers based on key that we define (for instances, client IP or request URL )
  - IP Hash :  Client IP address is used to determine which server receives the request.
  - Random with Two Choices : Pick two random servers and then apply Least Connections algorithm.

* Taken from NGINX
 
  ![load_Balancers](https://user-images.githubusercontent.com/75577090/172683095-db178931-75e0-4614-89fc-0345326d7e54.png)

  ### Health Check : ./health

 ## Distributed Tracing & Logging using Zipkin:
  
 #### Tracing : 
 - Tracing is a process of finding an execution path or a flow of multiple microservices to serve a request.
 
 #### Logging : 
 - Logging is a process of admitting all log messages for a trace. These messages can be of any type such as DEBUG, INFO, WARN, ERROR etc
 
 - Here, we will be implementing Logging and Tracing for multiple Microservices involved in serving one request. We also call it ‘Distributed Tracing and Logging’. Once it is implemented one time, then we don't need to check the code again to find the flow either for testing purpose or for debug purpose.
 
 #### Sleuth: 
 - Sleuth provides unique ids for the request flow. 
 - Further, the developer uses these ids to find out the flow of execution with the help of tools like Zipkin, ELK etc. 
 - Generally, it has two ids : 
   - Trace id : 
    - This is applicable for a complete flow. 
    - If the developer gets this Id, he/she can find the flow of execution in all microservices involved.
   - Span Id : 
    - This is applicable to the flow of one microservice. 
    - If the developer gets this Id, he/she can find the flow of execution in a particular microservice.
    - During the flow of execution, **span id** of the previous microservice becomes the **parent id** of the next microservice as shown below.

   ![image](https://user-images.githubusercontent.com/75577090/172776294-510b6cd5-29cd-4cf1-b8d8-1874a60db631.png)

 -  Spring Cloud Sleuth provides Spring Boot auto-configuration for distributed tracing.
 
 #### Zipkin : 
 - We will use Zipkin in two parts : Zipkin Client and Zipkin Server. 
 - Zipkin Client contains **Sampler** which collects data from microservices with the help of **Sleuth** and provides it to the Zipkin Server. 
 - In order to utilize the benefits of both tools, we should always add **Zipkin Client**’s dependency along with **Sleuth** in every microservice. 
 - However, there **must be only one centralized Zipkin Server**, which **collects** all data from **Zipkin Client** and **display** it as a UI. 
 - Hence, after making a request we should look into **Zipkin Server to find trace id, span id and even flow of execution**. From here itself, Open Log files to check Log lines that are related to the current trace Id.
