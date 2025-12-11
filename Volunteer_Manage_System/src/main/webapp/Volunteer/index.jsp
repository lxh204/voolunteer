<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>志愿者服务系统 - 主页</title>
    <style>
        body {
            font-family: 'Microsoft YaHei', Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        
        .header {
            background-color: #4CAF50;
            color: white;
            padding: 20px;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        }
        
        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 0 20px;
        }
        
        .activity-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 20px;
            padding: 20px;
            transition: transform 0.3s ease;
        }
        
        .activity-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
        }
        
        .activity-title {
            color: #333;
            font-size: 24px;
            margin: 0 0 10px 0;
        }
        
        .activity-details {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
            margin: 15px 0;
        }
        
        .detail-item {
            flex: 1;
            min-width: 200px;
        }
        
        .detail-label {
            font-weight: bold;
            color: #666;
        }
        
        .activity-description {
            color: #555;
            line-height: 1.6;
            margin: 15px 0;
        }
        
        .register-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 12px 24px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            float: right;
            transition: background-color 0.3s;
        }
        
        .register-btn:hover {
            background-color: #45a049;
        }
        
        .register-btn:disabled {
            background-color: #cccccc;
            cursor: not-allowed;
        }
        
        .status-badge {
            display: inline-block;
            padding: 5px 10px;
            border-radius: 20px;
            font-size: 14px;
            font-weight: bold;
        }
        
        .status-open {
            background-color: #e8f5e9;
            color: #4CAF50;
        }
        
        .status-closed {
            background-color: #ffebee;
            color: #f44336;
        }
        
        .search-bar {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        
        .search-input {
            width: 70%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        
        .search-btn {
            background-color: #2196F3;
            color: white;
            border: none;
            padding: 12px 20px;
            margin-left: 10px;
            border-radius: 4px;
            cursor: pointer;
        }
        
        .filter-section {
            margin: 20px 0;
            display: flex;
            gap: 15px;
        }
        
        .filter-btn {
            background-color: #e0e0e0;
            border: none;
            padding: 8px 16px;
            border-radius: 20px;
            cursor: pointer;
        }
        
        .filter-btn.active {
            background-color: #4CAF50;
            color: white;
        }
        
        .pagination {
            text-align: center;
            margin: 30px 0;
        }
        
        .page-btn {
            background-color: white;
            border: 1px solid #ddd;
            padding: 10px 15px;
            margin: 0 5px;
            cursor: pointer;
        }
        
        .page-btn.active {
            background-color: #4CAF50;
            color: white;
        }
        
        .clearfix::after {
            content: "";
            clear: both;
            display: table;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>志愿者服务系统</h1>
        <p>参与志愿服务，传递爱心力量</p>
        <div style="position: absolute; top: 20px; right: 20px;">
            <a href="profile.jsp" style="color: white; text-decoration: none; margin-right: 20px;">个人信息完善</a>
            <a href="myRegistrations.jsp" style="color: white; text-decoration: none;">我的报名</a>
        </div>
    </div>
    
    <div class="container">
        <div class="search-bar">
            <input type="text" class="search-input" placeholder="搜索志愿活动...">
            <button class="search-btn">搜索</button>
            
            <div class="filter-section">
                <button class="filter-btn active">全部活动</button>
                <button class="filter-btn">环境保护</button>
                <button class="filter-btn">社区服务</button>
                <button class="filter-btn">教育支持</button>
                <button class="filter-btn">敬老助残</button>
            </div>
        </div>
        
        <!-- 志愿活动列表 -->
        <div class="activity-list">
            <!-- 示例活动1 -->
            <div class="activity-card">
                <h2 class="activity-title">社区环保清洁活动</h2>
                <div class="activity-details">
                    <div class="detail-item">
                        <span class="detail-label">活动时间:</span>
                        <span>2023年10月15日 9:00-12:00</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">活动地点:</span>
                        <span>市中心公园</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">所需人数:</span>
                        <span>20人 (已报名: 12人)</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">状态:</span>
                        <span class="status-badge status-open">报名中</span>
                    </div>
                </div>
                <p class="activity-description">
                    参与社区环保清洁活动，共同维护我们美丽的家园。本次活动将清理公园内的垃圾，
                    宣传环保知识，提高市民环保意识。提供清洁工具和饮用水。
                </p>
                <div class="clearfix">
                    <button class="register-btn" onclick="registerActivity('1')">立即报名</button>
                </div>
            </div>
            
            <!-- 示例活动2 -->
            <div class="activity-card">
                <h2 class="activity-title">敬老院慰问演出</h2>
                <div class="activity-details">
                    <div class="detail-item">
                        <span class="detail-label">活动时间:</span>
                        <span>2023年10月20日 14:00-16:00</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">活动地点:</span>
                        <span>阳光敬老院</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">所需人数:</span>
                        <span>15人 (已报名: 15人)</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">状态:</span>
                        <span class="status-badge status-closed">已满员</span>
                    </div>
                </div>
                <p class="activity-description">
                    为敬老院的老人们带来精彩的文艺演出，陪伴他们度过愉快的下午时光。
                    需要有表演才艺的志愿者，包括唱歌、舞蹈、乐器演奏等。
                </p>
                <div class="clearfix">
                    <button class="register-btn" disabled>报名已满</button>
                </div>
            </div>
            
            <!-- 示例活动3 -->
            <div class="activity-card">
                <h2 class="activity-title">留守儿童课业辅导</h2>
                <div class="activity-details">
                    <div class="detail-item">
                        <span class="detail-label">活动时间:</span>
                        <span>每周六 10:00-12:00</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">活动地点:</span>
                        <span>希望小学</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">所需人数:</span>
                        <span>10人 (已报名: 5人)</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">状态:</span>
                        <span class="status-badge status-open">报名中</span>
                    </div>
                </div>
                <p class="activity-description">
                    为留守儿童提供课业辅导，帮助他们解决学习中的困难。
                    需要具备耐心和一定的教学能力，主要辅导小学课程。
                </p>
                <div class="clearfix">
                    <button class="register-btn" onclick="registerActivity('3')">立即报名</button>
                </div>
            </div>
        </div>
        
        <div class="pagination">
            <button class="page-btn active">1</button>
            <button class="page-btn">2</button>
            <button class="page-btn">3</button>
            <button class="page-btn">下一页</button>
        </div>
    </div>
    
    <script>
        function registerActivity(activityId) {
            // 这里应该调用后端API进行报名
            alert("您已成功报名活动ID: " + activityId + "！\n请等待管理员审核。");
            
            // 实际项目中应该发送AJAX请求到服务器
            // 示例:
            /*
            fetch('/volunteer/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({activityId: activityId})
            })
            .then(response => response.json())
            .then(data => {
                if(data.success) {
                    alert('报名成功！');
                    // 更新UI状态
                } else {
                    alert('报名失败：' + data.message);
                }
            });
            */
        }
        
        // 搜索功能
        document.querySelector('.search-btn').addEventListener('click', function() {
            const keyword = document.querySelector('.search-input').value;
            alert("搜索关键词: " + keyword + "\n实际项目中会根据关键词筛选活动");
        });
        
        // 回车搜索
        document.querySelector('.search-input').addEventListener('keypress', function(e) {
            if(e.key === 'Enter') {
                document.querySelector('.search-btn').click();
            }
        });
        
        // 筛选功能
        const filterButtons = document.querySelectorAll('.filter-btn');
        filterButtons.forEach(button => {
            button.addEventListener('click', function() {
                filterButtons.forEach(btn => btn.classList.remove('active'));
                this.classList.add('active');
                alert("筛选类别: " + this.textContent + "\n实际项目中会显示相应类别的活动");
            });
        });
    </script>
</body>
</html>
