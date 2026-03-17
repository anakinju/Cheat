# 🎮 Cheat - Immersive Stealth Game / 沉浸式潜入游戏

![Java](https://img.shields.io/badge/Language-Java-orange.svg)
![Framework](https://img.shields.io/badge/Framework-Swing-blue.svg)
![Status](https://img.shields.io/badge/Status-Complete-green.svg)

---

## 🌍 Language / 语言
[English Version](#english-version) | [中文版本](#chinese-version)

---

<a name="english-version"></a>
## 🌟 Project Overview (English)

**"Cheat"** is a stealth-based narrative game developed as a group project for the CS 201 Java course. I served as the **Lead Developer and Architect**, spearheading the entire process from the initial game concept and plot design to the final Java implementation.

In this game, players take on the role of a student during a midterm exam, attempting to find "shortcuts" to success. You must navigate the classroom, collect hidden cheatsheets, and avoid the teacher's dynamic field of vision. It is a test of reflexes, timing, and strategic movement.

### 🎨 Creative & Visual Design
*   **Narrative Design**: Independently conceived the game's world-building and mission flow, integrating the "stealth-collect-return" mechanic with a high-stakes classroom narrative.
*   **Visual Assets**: The game's visual style was curated through a combination of **AI-generated imagery** and **online resources**, which were then post-processed to fit the game's atmosphere and perspective requirements.

### 🛠️ Technical Implementation
The project is built using **Java Swing**, featuring:
1.  **Finite State Machine (FSM) Vision System**:
    *   The teacher's behavior is governed by a state machine driven by `TimerTask`, enabling 4-directional orientation changes.
    *   Dynamic rendering of the "vision cone" using `AlphaComposite` to provide real-time visual feedback to the player.
2.  **Precise Collision Detection**:
    *   Utilizes `java.awt.Rectangle` for interaction logic between the player and collectible items (`paperball`).
    *   Real-time spatial partitioning to determine if the player's coordinates intersect with the teacher's active vision area.
3.  **Scene & State Management**:
    *   Seamless transition between the classroom overview and specific "Desk" views via `KeyListener`.
    *   Comprehensive win/loss logic: Players must not only collect items but also successfully return to their seat (`reachDesk`)方可获胜。

### 🕹️ How to Run
1.  **Requirement**: JDK 8 or higher.
2.  **Run**: Execute `src/com/sxt/GameWin.java` in your IDE.

### 🎮 Controls
*   **Arrow Keys**: Move the student.
*   **Goal**: Collect all cheatsheets and return to your desk without being seen.

### 👨‍💻 Team & Roles
*   **[Linxi Ju] (Lead Developer & Architect)**: Responsible for the entire game engine logic, FSM vision system, narrative design, and core visual asset integration.
*   **Collaborators**: Assisted with presentation (PPT) and initial visual asset sourcing.

---

<a name="chinese-version"></a>
## 🌟 项目概述 (中文)

**《Cheat》** 是一款作为 CS 201 Java 课程小组项目开发的潜入类剧情游戏。我担任了**首席开发者与架构师**的角色，主导了从最初的游戏设想、剧情设计到最终 Java 代码实现的完整流程。

在游戏中，玩家扮演一名在期中考试中试图寻找“捷径”的学生。你需要在教室内灵活移动，收集散落的纸条，同时时刻警惕老师动态变化的视线。这不仅是对手速的考验，更是对时机把握与策略移动的博弈。

### 🎨 创意与视觉设计
*   **剧情设计**：独立构思了游戏的世界观与任务流，将“潜入-收集-回归”的机制与紧张的教室叙事深度结合。
*   **视觉资产**：游戏的视觉风格通过 **AI 生成图像**与**网络资源**整合而成，并经过后期处理以适配游戏的透视需求与氛围。

### 🛠️ 技术实现
项目基于 **Java Swing** 开发，核心技术点包括：
1.  **有限状态机 (FSM) 视线系统**：
    *   老师的行为逻辑由 `TimerTask` 驱动的状态机控制，实现 4 个方向的随机/定时转向。
    *   利用 `AlphaComposite` 动态渲染“视线锥体”，为玩家提供实时的视觉反馈。
2.  **精确碰撞检测**：
    *   利用 `java.awt.Rectangle` 实现玩家与收集品（小纸条）的交互逻辑。
    *   实时空间判定算法，用于检测玩家坐标是否与老师的活跃视线区域重叠。
3.  **场景与状态管理**：
    *   通过 `KeyListener` 实现教室全景与特定“课桌”视图之间的无缝切换。
    *   完善的胜负逻辑：玩家不仅要收集道具，还必须成功回到座位（`reachDesk`）方可获胜。

### 🕹️ 如何运行
1.  **要求**: JDK 8 或更高版本。
2.  **运行**: 在 IDE 中运行 `src/com/sxt/GameWin.java`。

### 🎮 操作说明
*   **方向键**: 控制学生移动。
*   **目标**: 避开视线，收集所有纸条并回到座位。

### 👨‍💻 团队与分工
*   **[鞠林汐] (首席开发者 & 架构师)**：负责全部游戏引擎逻辑、FSM 视线系统、剧情设计以及核心视觉资产整合。
*   **组员**：协助完成项目汇报（PPT）及部分初始视觉素材搜集。
