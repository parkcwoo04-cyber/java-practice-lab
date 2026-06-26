

## 1. Core Theme

The main theme of this forum was how **Physical AI can move beyond research lab demonstrations and become connected to real-world industrial and everyday environments**.

Robots that work well in research labs often face many limitations when deployed in the real world.

```text
Research Lab → Controlled environment
Real World → Unstructured, dynamic, risky environment
```

Real-world deployment introduces many constraints:

- Safety
- Maintenance
- System integration
- Task speed
- Cost
- Human-centered environments
- Unstructured processes
- Complex decision-making
- Data scarcity
- Hardware durability

Therefore, the key challenge of Physical AI is not only making AI models smarter.  
It is about **implementing AI inside robotic systems that can operate reliably in unstable, complex, and physical environments**.

---

## 2. Main Insight

Physical AI is still in an early commercialization phase.  
Many robots can demonstrate impressive capabilities, but they are often still slower, more expensive, less reliable, and harder to maintain than humans in real-world operations.

However, just as LLMs such as ChatGPT improved rapidly after their early stages, Physical AI may also develop quickly. The important point is to understand the structure and direction of this field before it becomes mature.

```text
Physical AI is not only about robots.
It is about connecting AI, hardware, data, safety, and real-world operations.
```

---

# Company Sessions

---

## 3. Agile Robots  
### From Research to Industry: Connecting AI to the Physical World

Agile Robots focused on **connecting AI to robotics and applying it to real industrial environments**.

### Target Industries

Agile Robots presented the following target industries:

- AI & Technology
- Consumer Electronics
- Automotive
- Medical & Healthcare
- Logistics

### General Portfolio

Agile Robots has a broad robotics portfolio, including:

- Industrial robotic arms
- Dexterous hands
- AGV / AMR
- Mobile single-arm robots
- Mobile semi-humanoid robots
- General-purpose humanoid robots

Representative products and platforms mentioned include:

- Franka
- Diana
- Agile Hand

These products show that commercially available robotic platforms already exist.  
However, the level of real-world adoption and scale may vary by product and use case.

### Franka as a Research Platform

Franka was positioned as a leading robotics research platform.  
One of the slides emphasized **research publications mentioning Franka**, suggesting that Franka is not only a commercial robotic arm but also an important platform widely used in robotics research labs.

This makes Franka especially relevant for:

- Robotics research
- AI manipulation experiments
- Force control
- Robot learning
- Academic and industrial R&D

### Key Components of Physical AI

Agile Robots framed Physical AI around three important components:

```text
1. Robotic Body
2. Robotic Brain
3. Robotic Data
```

| Component | Meaning |
|---|---|
| Robotic Body | The physical robot hardware that moves and interacts with the world |
| Robotic Brain | AI models responsible for perception, planning, decision-making, and control |
| Robotic Data | Data used to train, evaluate, and improve robot behavior |

### Data Strategy

A major theme was the importance of data collection and data quality.

Agile Robots mentioned multiple types of data:

- Real-world robot data
- Simulation data
- Human operation data
- Internet-scale visual data

The key point is that robot learning does not only require large amounts of data.  
It requires **high-quality data that is closely connected to real physical tasks**.

### My Interpretation

Agile Robots appears to approach Physical AI from an industrial deployment perspective.  
Rather than treating Physical AI only as a future concept, the company is trying to connect AI models, robotic hardware, and real industrial use cases.

The main goal seems to be building robotic systems that can function even in unstable and complex environments.

---

## 4. Discover Robotics  
### Embodied AI: Real World in Action

Discover Robotics emphasized the shift from **digital AI** to **physical AI**.

```text
Digital AI → Screen Revolution
Physical AI → Next Evolutionary Frontier
```

### Key Example: Clothes Folding Robot

One of the most interesting examples was a **clothes folding robot**.

Folding clothes is simple for humans but extremely difficult for robots because:

- Clothes constantly change shape
- The robot must identify where and how to grasp the fabric
- Force control is required
- Visual information alone may be insufficient
- The robot must adapt its motion depending on the state of the object

Discover Robotics emphasized that the robot performs the task with:

- No task-specific pre-programming
- No direct human interference
- Heavy reliance on AI-based perception and control

A more precise way to describe this is:

```text
The robot performs the task without pre-programmed task-specific rules or direct human intervention, relying heavily on AI-based perception and control.
```

This example shows that the goal of Physical AI is not only to automate fixed and repetitive tasks.  
The goal is to enable robots to handle flexible, everyday physical tasks.

### From One Skill to Many Skills

Clothes folding may look like one specific skill, but it requires multiple core capabilities:

- Multimodal perception
- Motion planning
- Force control
- Reinforcement learning
- Object state understanding

If these capabilities generalize, they may eventually extend to other household tasks such as:

- Cooking
- Dishwashing
- Cleaning
- Other home service tasks

### Self-Evolving Systems

The term **self-evolving** was also mentioned.

This should be interpreted carefully.  
Rather than meaning that a robot can automatically improve itself without limits, it is more accurate to understand it as:

```text
Data-driven continuous improvement
```

or:

```text
Learning from feedback loops
```

In other words, the robot system improves over time as it collects more data, receives feedback, and updates its models.

---

## 5. PsiBot  
### From Human Data to Dexterous Robot Intelligence

PsiBot focused on the idea that **data is the primary bottleneck for embodied AI foundation models**.

### Core Problem: Data Bottleneck

LLMs were able to scale by using massive internet text data.  
Physical AI is different because robots need action-oriented, physical-world data.

Robots require data such as:

- Object manipulation data
- Hand and arm movement data
- Force control data
- Failure cases
- Behavior data from diverse environments
- Human demonstration data

This is why PsiBot emphasized **human data** as a key source of embodied AI learning.

### Human Data

One slide stated:

```text
Human data simultaneously scales in volume, precision, and diversity.
```

This means human data can provide:

- Scale
- Precision
- Diversity

Robot data and simulation data are useful, but they may be limited in one or more of these dimensions.

### Simulation Limits

PsiBot also emphasized that simulation has limitations.

```text
Physics engines have limited accuracy.
In real life, robots often face more complex conditions.
```

Simulation is useful, but it cannot fully reproduce all real-world friction, deformation, uncertainty, contact dynamics, and environmental variation.

Therefore, real human behavior data and real robot execution data become very important.

### Psi-R2 and Psi-W0

PsiBot introduced a closed-loop framework involving **Psi-R2** and **Psi-W0**.

Based on the slide:

```text
Psi-R2 = World Action Model / Policy Model
Psi-W0 = Action-Conditioned World Model
```

The structure can be understood as:

```text
Image + State + Text
→ Psi-R2 predicts action chunks
→ Action + Image + State + Text
→ Psi-W0 rolls out future frames
→ Verifier / RL evaluates, filters, and relabels
→ Robotized Dataset is refreshed
```

### Interpretation of Psi-W0

Psi-W0 should not be understood as only a data collection model.  
A more accurate interpretation is that Psi-W0 is an **action-conditioned world model** used inside a closed-loop system for:

- Future-frame rollout
- Action imagination
- Verification
- Data generation
- Dataset refresh
- Self-improvement through feedback

This suggests that PsiBot is trying to build a scalable data feedback loop for embodied AI.

### Data Feedback Loop

The overall loop can be summarized as:

```text
Scenario implementation
→ Data collection
→ Policy evaluation
→ Data generation
→ Model improvement
→ Real-world deployment
```

PsiBot also connected this idea to logistics.  
Logistics sortation may create a strong synergy with robot data collection because it provides repeated but diverse real-world manipulation scenarios.

### My Interpretation

PsiBot seems to focus less on only building robot hardware and more on building the **data pipeline and learning system for robot intelligence**.

Compared with Agile Robots, which emphasizes industrial platforms and deployment, PsiBot emphasizes:

- Human data
- Dexterous manipulation
- Policy learning
- World models
- Dataset refresh
- Closed-loop self-improvement

---

## 6. Unitree  
### From Robot to Physical Intelligence

Unitree’s presentation was especially useful because it provided a market adoption framework and an honest view of the current state of Physical AI.

---

## Robot Market Priority Pyramid

Unitree presented a robot market adoption hierarchy from **high-risk necessity** to **comfort-driven desirability**.

| Tier | Category | Scenarios | Interpretation |
|---|---|---|---|
| T1 | 4D High-Hazard Operations | Mining operations, HV substation inspection, disaster search | Highest urgency; robots provide safety value |
| T2 | Uncomfortable Scenario | High temperature, humidity, noise, vibration, dusty environments | Robots reduce human discomfort and exposure |
| T3 | Industrial & Logistics | Warehouses, factory inspection, sorting | Productivity and automation-driven adoption |
| T4 | Commercial Service | Concierge, malls, event shows | Experience, service, and branding value |
| T5 | Consumer / Comfort | Home assistants, entertainment | Lowest urgency, highest cost sensitivity |

The slide also suggested the following relationship:

```text
Hazard Level → Adoption Urgency → Cost Sensitivity
```

This means that higher-risk environments create stronger adoption urgency and may justify higher costs.  
In contrast, consumer and comfort-oriented applications may have large long-term potential but also higher cost sensitivity.

### Robotics as New IoT

The idea of robotics as a **new form of IoT** was also important.

```text
IoT = sensing the physical world
Robotics = sensing + moving + acting in the physical world
```

Robots can be viewed as physical IoT devices that not only collect data but also act on the environment.

### Entertainment and Experience

Unitree also emphasized the importance of entertainment and experience.

This is interesting because robotics adoption may not be driven only by productivity.  
Visual impact, interactivity, user experience, and branding value can also influence early adoption.

---

## Unitree B2: Firefighting Robot

Unitree presented firefighting robots as one of the representative real-world applications of its robotics technology.

In this context, the Unitree B2/B2-class platform can be understood as more than a general quadruped robot.  
It represents how robot mobility, hardware robustness, edge computing, and Physical AI capabilities can be applied to emergency-response scenarios.

A balanced description would be:

```text
Unitree presented firefighting robots as a representative application case. This shows how quadruped robots can move beyond demonstrations and be positioned for high-risk emergency-response environments. However, the exact level of deployment scale should be distinguished between demonstration, pilot deployment, and full operational use.
```

---

## Unitree H2 PLUS

Unitree also presented **Unitree H2 PLUS** as a coming-soon humanoid platform.

Key specifications shown on the slide:

- NVIDIA powered
- 2,070 TFLOPS FP4 AI performance using Jetson Thor
- 75 DOF
- Full-body + dexterous hands
- 15 kg peak arm payload
- 3 hours battery endurance

Since the slide labeled it as **Coming Soon**, it should be treated as an upcoming platform rather than a fully commercialized product.

### Interpretation

H2 PLUS highlights Unitree’s direction toward humanoid robots with:

- Strong onboard AI compute
- Full-body mobility
- Dexterous hands
- Higher payload
- Longer battery endurance
- Integrated R&D and full-stack development

This also reinforces Unitree’s message that hardware capability remains essential for Physical AI.

---

## Three Core Priorities

Unitree emphasized three important priorities:

```text
1. Brain-body co-evolution
2. Manufacturing leap
3. Edge compute decentralization
```

| Priority | Meaning |
|---|---|
| Brain-body co-evolution | AI models and robot hardware must evolve together |
| Manufacturing leap | Robots must be manufactured reliably and at scale |
| Edge compute decentralization | Robots should process data close to the physical environment instead of relying only on cloud systems |

### Edge System

Edge computing is especially important in robotics because robots must react quickly in the physical world.

Examples:

```text
Obstacle detected → Stop immediately
Heat source detected → Adjust path
Human nearby → Slow down
Network disconnected → Continue safe operation
```

These decisions should happen inside the robot or near the field environment, not only in the cloud.

---

## Controlled Performance vs. Real-World Operations

Unitree presented a very honest slide comparing controlled performance with real-world operations.

### 1. “Attention-Grabbing” vs. Productivity

Robotic demos such as dancing or flipping may attract attention, but they should be separated from actual industrial productivity.

The key is to focus on task completion metrics:

- Units processed per hour
- Task success rate
- Average task completion time
- Human intervention rate
- Cost per task

### 2. Pilot Deployments, Not Scale

The slide emphasized that humanoids are appearing in factories and hospitals, but the commercial loop is not fully closed yet.

A key timeline was:

```text
Demonstrations → Pilot 2025-26 → Commercial 2027-30 → General Labor 2030+
```

This suggests that the current stage is still closer to demonstrations and pilot deployments rather than full large-scale adoption.

### 3. The Dexterity Bottleneck

The dexterity bottleneck refers to the difficulty of unsupervised long-horizon manipulation in complex human environments.

In other words, robots still struggle with tasks that require:

- Long task sequences
- Fine manipulation
- Complex object interaction
- Adaptation to changing environments
- Human-like physical flexibility

Unitree’s proposed direction included:

```text
WAM + dataset compounding
Solving dexterity through data at scale
```

This suggests that dexterity may improve through larger datasets, better world/action models, and continuous data accumulation.

---

# Cross-Company Comparison

| Company | Main Focus | Key Bottleneck Addressed |
|---|---|---|
| Agile Robots | Industrial robotics and Physical AI deployment | Bridging AI and industrial automation |
| Discover Robotics | Everyday embodied AI tasks | Generalizing from one physical skill to many tasks |
| PsiBot | Human data and dexterous robot intelligence | Data bottleneck for embodied AI foundation models |
| Unitree | Robot hardware, market adoption, and edge deployment | Affordable/scalable hardware and real-world mobility |

---

## Investor Perspective on Physical AI

Major investment banks and asset managers increasingly view Physical AI and humanoid robotics as a long-term automation and productivity theme.

The common investment thesis is that AI will not remain limited to digital screens. As AI moves into robots, industrial machines, vehicles, drones, and other physical systems, it may create a new wave of automation in the real world.

However, investors generally do not see Physical AI as a fully mature market yet. The current stage is closer to:

Demonstration → Pilot Deployment → Commercial Scaling → General Labor

This matches the timeline discussed in the forum, where many robotics companies are still moving from impressive demonstrations toward measurable productivity and scalable commercial use.

### Key Investor Questions

From an investor perspective, the main question is not simply whether a robot can perform an impressive demo.

The more important questions are:

- Can the robot complete real tasks reliably?
- Can it improve productivity compared to human labor or existing automation?
- Can it reduce cost per task?
- Can it operate safely in real environments?
- Can pilot deployments turn into repeatable commercial contracts?
- Can the company scale manufacturing and maintenance?
- Can the robot continue improving through data feedback loops?

In other words:

Demo performance does not equal commercial value.  
Pilot deployment does not equal scale.  
Commercial availability does not equal commercial success.

### What Investors Are Watching

Investors are not only watching robot manufacturers. They are also watching the entire Physical AI value chain.

Important areas include:

- AI foundation models
- Vision-language-action models
- Robot data collection
- Human operation data
- Simulation platforms
- Sensors
- Actuators and motors
- Batteries
- Edge computing
- Dexterous hands
- Robot manufacturing
- Maintenance and deployment services
- System integration

This means the strongest companies may not simply be the companies with the most impressive robot demo. The strongest companies may be those that can connect the full stack:

Robot body + Robot brain + Robot data + Manufacturing + Deployment operations

### Bullish Investor View

The optimistic view is that Physical AI could become a major long-term market because of several structural trends:

- Labor shortages
- Aging populations
- Rising labor costs
- Demand for automation in manufacturing and logistics
- Need for robots in dangerous or uncomfortable environments
- Rapid improvement in AI models
- Falling hardware and component costs
- Better sensors and edge computing

Under this view, Physical AI could eventually expand from industrial use cases into service, healthcare, logistics, and household environments.

### Cautious Investor View

The cautious view is that robotics is much harder than digital AI.

Physical AI requires not only good models, but also reliable hardware, safety systems, real-world data, maintenance, and integration with existing workflows.

Major risks include:

- Robots remain impressive demos but fail to create productivity
- Pilot deployments do not convert into scaled contracts
- Dexterous manipulation remains too difficult
- Hardware cost remains too high
- Maintenance is too expensive
- Safety and liability issues slow adoption
- Real-world data collection remains difficult
- The winning form factor may not be humanoid

This is why many investors are interested in the theme but still cautious about which companies will actually win.