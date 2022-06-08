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

