#exec(open("C:\\1programming\\Battle Commander.py").read())
class INFANTRY:
	MAXHEALTH=5
	TYPE='melee'
	HITS=2
	MOVE=2
	CANRETREAT=1
	RETREATHIT=0
	def __init__(self,player,health,tile,movefrom,moveto):
		self.player=player
		self.health=health
		self.tile=tile
		self.movefrom=movefrom
		self.moveto=moveto
class CAVALRY:
	MAXHEALTH=3
	TYPE='melee'
	HITS=2
	MOVE=1
	CANRETREAT=1
	RETREATHIT=1
	def __init__(self,player,health,tile,movefrom,moveto):
		self.player=player
		self.health=health
		self.tile=tile
		self.movefrom=movefrom
		self.moveto=moveto
class ARTILLERY:
	MAXHEALTH=2
	TYPE='ranged'
	HITS=1
	RANGEHITS=(2,1,1)
	MOVE=3
	CANRETREAT=0
	RETREATHIT=0
	def __init__(self,player,health,tile,movefrom,moveto):
		self.player=player
		self.health=health
		self.tile=tile
		self.movefrom=movefrom
		self.moveto=moveto
class NOTERRAIN:
	DEFENSE=0
	ATTACK=0
	RANGE=2
class HILL:
	DEFENSE=1
	ATTACK=0
	RANGE=3
class TOWN:
	DEFENSE=2
	ATTACK=0
	RANGE=2
class PLAYER:
	def __init__(self,vp):
		self.vp=vp	
def GENERATE():
	global board,i1,i2,i3,i4,i5,i6,i7,c1,c2,c3,c4,a1,a2,a3,unitlist
	board=[[NOTERRAIN,NOTERRAIN,NOTERRAIN,NOTERRAIN,NOTERRAIN,NOTERRAIN,NOTERRAIN],[NOTERRAIN,NOTERRAIN,HILL,TOWN,HILL,TOWN,NOTERRAIN],[NOTERRAIN,TOWN,HILL,HILL,HILL,HILL,HILL,NOTERRAIN],[NOTERRAIN,NOTERRAIN,NOTERRAIN]]
	i1=INFANTRY(1,INFANTRY.MAXHEALTH,(2,2),(),())
	i2=INFANTRY(1,INFANTRY.MAXHEALTH,(2,4),(),())
	i3=INFANTRY(1,INFANTRY.MAXHEALTH,(2,6),(),())
	i4=INFANTRY(1,INFANTRY.MAXHEALTH,(3,2),(),())
	i5=INFANTRY(2,INFANTRY.MAXHEALTH,(3,6),(),())
	i6=INFANTRY(2,INFANTRY.MAXHEALTH,(4,2),(),())
	i7=INFANTRY(2,INFANTRY.MAXHEALTH,(5,2),(),())
	c1=CAVALRY(1,CAVALRY.MAXHEALTH,(1,4),(),())
	c2=CAVALRY(1,CAVALRY.MAXHEALTH,(1,5),(),())
	c3=CAVALRY(2,CAVALRY.MAXHEALTH,(4,6),(),())
	c4=CAVALRY(2,CAVALRY.MAXHEALTH,(5,1),(),())
	a1=ARTILLERY(1,ARTILLERY.MAXHEALTH,(1,3),(),())
	a2=ARTILLERY(2,ARTILLERY.MAXHEALTH,(4,3),(),())
	a3=ARTILLERY(1,ARTILLERY.MAXHEALTH,(4,5),(),())
	unitlist=[i1,i2,i3,i4,i5,i6,i7,c1,c2,c3,c4,a1,a2,a3]
#def RETREAT(moving):
	#if moving.movefrom==moving.moveto:
def MOVE(moving):
	moving.movefrom=moving.tile
	moving.tile=moving.moveto
def KILLCHECK():
	global unitlist
	for a in unitlist:
		if a.health<1:
			battlelist.remove(a)
			unitlist.remove(a)
player1=PLAYER(0)
player2=PLAYER(0)
movelist=[]
battlelist=[]
battleunits=[]
player1hits=0
player2hits=0
GENERATE()
while player1.vp<20 or player2.vp<20 and player1.vp!=player2.vp:
	#player input code
	#movement
	for a in unitlist:
		MOVE(a)
		if a.tile in movelist:
			if a.tile not in battlelist:
				battlelist.append(a.tile)
			else:
				movelist.append(a.tile)
	#combat
	for a in battlelist:
		for b in unitlist:
			if b.tile==a:
				battleunits.append(b)
		for a in battleunits:
			if a.player==1:
				player1hits+=round(a.HITS-(a.MAXHEALTH-a.health)/2)
			else:
				player2hits+=round(a.HITS-(a.MAXHEALTH-a.health)/2)
		for a in battleunits:
			if a.player==1:
				while a.health>0 and player2hits>0:
					a.health-=1
					player2hits-=1
			if a.player==2:
				while a.health>0 and player1hits>0:
					a.health-=1
					player1hits-=1
		battleunits=[]
	KILLCHECK()
	#retreats
	for a in battlelist:
		for b in unitlist:
			if b.tile==a:
				battleunits.append(b)
		for a in battleunits:
			if a.tile!=a.movefrom:
	
	KILLCHECK()
	movelist=[]
	battlelist=[]
if player1.vp>player2.vp:
	print('Player1 wins')
else:
	print('Player2 wins')