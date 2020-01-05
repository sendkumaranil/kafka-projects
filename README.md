# kafka-projects
Kafka Brokers, Kafka-Consumer, Kafka-Producer, Secure Kafka endpoint with Okta Oauth2.0
<hr/>
<h5> Download Kafka here: <a href="https://kafka.apache.org/downloads">download</a></h5>
<hr/>
<h4> Start Zookeeper:</h4>
<p>Kafka uses ZooKeeper so you need to first start a ZooKeeper server if you don't already have one. You can use the convenience script packaged with kafka to get a quick-and-dirty single-node ZooKeeper instance.</p>
      <span>
          > bin/zookeeper-server-start.sh ../config/zookeeper.properties
      </span>
<hr/>
<h4> Start Server:</h4>
<p>Open new terminal and run below command:</p>
<span>
      > bin/kafka-server-start.sh config/server.properties
</span>
<hr/>
<h4> Create Topic:</h4>
<p>Open new terminal and run below command:</p>
<span>
      > bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic mytesttopic
</span>
<hr/>
<h4> Start Consumer Spring boot project</h4>
<h4> Start Producer Spring boot project</h4>

<h4> Send Messgae</h4>
<p>cURL Command:</p>
<span>
                        curl -X POST \
                    http://localhost:8282/kafka-producer/send \
                    -d '{
                        "messageId":"1001",
                        "messages":"Hello World"
                  }'
</span>
<hr/>
<h4> Secure kafka-producer endpoint with okta oauth2</h4>
<p>Prerequisite:</p>
<ol>
      <li>Create Okta Account <a href="https://developer.okta.com/signup" >here</a></li>
      <li>Login to your okta account</li>
      <li>Go To Application</li>
      <li>Add app name, redirect-url and save</li>
      <li> copy client-id and secret code</li>
 </ol>
 <p>Open kafka-producer spring boot app</p>
 <p> Add following into to the application.yml file</p>
 <p>
            okta:
              oauth2:
                issuer: https://{your-Okta-domain}/oauth2/default
                client-id: {your okta client-id}
                client-secret: {your okta client-secret}
 </p>
      
